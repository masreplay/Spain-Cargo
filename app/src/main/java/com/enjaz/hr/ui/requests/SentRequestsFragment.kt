package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.enjaz.hr.R
import com.enjaz.hr.data.model.getLeaveRequests.Item
import com.enjaz.hr.data.model.getLeaveRequests.LeavesResponse
import com.enjaz.hr.databinding.FramgnetSentRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.util.Constants.ITEMS_PER_PAGE
import com.enjaz.hr.util.snackBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SentRequestsFragment :
    BaseFragment<FramgnetSentRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener, ISRequestsItemActionListener {

    // TODO: 2/2/2021 reload and no data

    private val requestsViewModel: RequestsViewModel by viewModels()


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

        val lm = LinearLayoutManager(requireActivity())

        getViewDataBinding().rv.apply {
            adapter = sentRequestsAdapter
            layoutManager = lm
        }

        getViewDataBinding().stl.setOnRefreshListener {
            maxSkip = 0
            skip = 0
            requests.clear()
            getViewDataBinding().lytNoData.hide()
            getViewModel().getLeaveRequests(
                skipCount = skip
            )
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