package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.enjaz.hr.R
import com.enjaz.hr.data.model.Status
import com.enjaz.hr.data.model.getLeaveRequests.Item
import com.enjaz.hr.data.model.getLeaveRequests.LeavesResponse
import com.enjaz.hr.databinding.FramgnetSentRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.sendRequest.SendRequestViewModel
import com.enjaz.hr.util.Constants.ITEMS_PER_PAGE
import com.enjaz.hr.util.snackBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SentRequestsFragment :
    BaseFragment<FramgnetSentRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener, ISRequestsItemActionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()
    val sedRequestViewModel: SendRequestViewModel by activityViewModels()


    private lateinit var sentRequestsAdapter: SentRequestsAdapter
    var requests = mutableListOf<Item>()
    private var maxSkip: Int = 0
    var skip: Int = 0
    var isLoading = false

    override fun getLayoutId(): Int {
        return R.layout.framgnet_sent_requests
    }

    override fun getViewModel(): RequestsViewModel {
        return requestsViewModel
    }


    override fun getNavigator(): IRequestsInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sedRequestViewModel.missPunchResponse.observe(requireActivity(), Observer { resource ->
            resource?.let {
                if (it.status == Status.SUCCESS)
                    refreshRequests()
            }
        })

        sedRequestViewModel.overTimeResponse.observe(requireActivity(), Observer { resource ->
            resource?.let {
                if (it.status == Status.SUCCESS)
                    refreshRequests()
            }
        })

        sedRequestViewModel.sendLeaveRequestResponse.observe(
            requireActivity(),
            Observer { resource ->
                resource?.let {
                    if (it.status == Status.SUCCESS)
                        refreshRequests()
                }
            })

        getViewModel().leaveRequestResponse.observe(requireActivity(), Observer { resource ->
            resource?.let {
                if (it.status == Status.SUCCESS || it.status == Status.ERROR)
                    getViewDataBinding().stl.isRefreshing = false
                if (it.status == Status.ERROR && maxSkip == 0)
                    getViewDataBinding().constraintError.show()
                if (it.status == Status.LOADING)
                    getViewDataBinding().constraintError.hide()

            }
        })

        getViewDataBinding().imageError.setOnClickListener {
            getViewDataBinding().constraintError.hide()
            getViewModel().getLeaveRequests(skipCount = skip)
        }

        val lm = LinearLayoutManager(requireActivity())

        getViewDataBinding().rv.apply {
            adapter = sentRequestsAdapter
            layoutManager = lm
        }

        getViewDataBinding().stl.setOnRefreshListener {
            refreshRequests()
        }


        getViewDataBinding().rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount: Int = lm.childCount
                val totalItemCount: Int = lm.itemCount
                val firstVisibleItemPosition: Int = lm.findFirstVisibleItemPosition()



                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && skip + ITEMS_PER_PAGE <= maxSkip
                    && !isLoading
                ) {
                    isLoading = true
                    skip += ITEMS_PER_PAGE

                    getViewModel().getLeaveRequests(
                        skipCount = skip
                    )

                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewModel().getLeaveRequests(skipCount = skip)

        sentRequestsAdapter = SentRequestsAdapter(requireContext(), requests)
        sentRequestsAdapter.setHasStableIds(true)
        sentRequestsAdapter.setOnItemClickListener(this)

    }


    override fun onSuccess(leavesResponse: LeavesResponse) {
        if (leavesResponse.items.isEmpty() && requests.isEmpty()) {
            getViewDataBinding().lytNoData.show()
        } else {
            maxSkip = leavesResponse.totalCount
            requests.addAll(leavesResponse.items)
            sentRequestsAdapter.setItemsList(requests)
            isLoading = false
        }
    }

    override fun noRequests() {
        getViewDataBinding().lytNoData.show()
    }

    override fun requestsAvailable() {
        getViewDataBinding().lytNoData.hide()
    }

    override fun showSnack(string: String, color: String, drawable: Int?) {
        snackBar(string, drawable, color, getViewDataBinding().parent, requireContext())
    }

    override fun onFabClick() {
    }

    override fun onHistorySuccess(response: LeavesResponse) {
    }

    override fun onChangeRequestSuccess() {
        refreshRequests()
    }

    private fun refreshRequests() {
        maxSkip = 0
        skip = 0
        requests.clear()
        getViewDataBinding().lytNoData.hide()
        getViewModel().getLeaveRequests(
            skipCount = skip
        )
    }

    override fun onShowMoreClicked() {
    }

    override fun onChangeRequestFailure() {
    }

    override fun onChangeRequestNetwork() {
    }

    override fun onCancelClick(leave: Item) {
        if (leave.workflowStatus == "WaitingForApproval") {
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle(getString(R.string.casncel_request))
                .setMessage(getString(R.string.msg_cancel))
                .setNegativeButton(getString(R.string.dismiss)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                    getViewModel().changeRequestStatus(
                        leave.workflowCorrelationId,
                        3,
                        false
                    )
                    dialog.dismiss()
                }
                .show()
        }
    }
}