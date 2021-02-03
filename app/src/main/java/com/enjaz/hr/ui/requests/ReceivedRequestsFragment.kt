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
import com.enjaz.hr.databinding.FramgnetReceivedRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.util.Constants
import com.enjaz.hr.util.snackBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceivedRequestsFragment :
    BaseFragment<FramgnetReceivedRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener, IRRequestsItemActionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()

    private lateinit var receiveRequestsAdapter: ReceivedRequestsAdapter


    private lateinit var receivedHistoryRequestsAdapter: HistoryReceivedRequestsAdapter
    var requests = mutableListOf<Item>()
    private var maxSkip: Int = 0
    var skip: Int = 0
    var isLoading = false

    override fun getLayoutId(): Int {
        return R.layout.framgnet_received_requests
    }

    override fun getViewModel(): RequestsViewModel {
        return requestsViewModel
    }


    override fun getNavigator(): IRequestsInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getViewDataBinding().stl.setOnRefreshListener {
            maxSkip = 0
            skip = 0
            requests.clear()
            getViewModel().getLeaveRequests(skipCount = 0, maxResult = 2, isManager = true)
            getViewModel().getOldLeaveRequests(skipCount = skip)
        }

        getViewDataBinding().nested.isNestedScrollingEnabled = false


        getViewDataBinding().rv.apply {
            adapter = receiveRequestsAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        val lm = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        getViewDataBinding().rvHistory.apply {
            adapter = receivedHistoryRequestsAdapter
            layoutManager = lm
        }

        getViewDataBinding().rvHistory.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount: Int = lm.childCount
                val totalItemCount: Int = lm.itemCount
                val firstVisibleItemPosition: Int = lm.findFirstVisibleItemPosition()



                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && skip + Constants.ITEMS_PER_PAGE <= maxSkip
                    && !isLoading
                ) {
                    isLoading = true
                    skip += Constants.ITEMS_PER_PAGE

                    getViewModel().getOldLeaveRequests(
                        skipCount = skip
                    )

                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveRequestsAdapter = ReceivedRequestsAdapter(requireContext(), mutableListOf())
        receiveRequestsAdapter.setOnItemClickListener(this)

        receivedHistoryRequestsAdapter =
            HistoryReceivedRequestsAdapter(requireContext(), requests)
        receivedHistoryRequestsAdapter.setOnItemClickListener(this)
        receivedHistoryRequestsAdapter.setHasStableIds(true)

        getViewModel().getLeaveRequests(skipCount = 0, maxResult = 2, isManager = true)
        getViewModel().getOldLeaveRequests(skipCount = skip)


    }

    override fun onAcceptClick(item: Item) {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Accept Request")
            .setMessage("Are you sure you want to accept this request")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Accept") { dialog, _ ->
                getViewModel().changeRequestStatus(item.workflowCorrelationId, 1, true)
                dialog.dismiss()
            }
            .show()
    }

    override fun onDeclinedClick(item: Item) {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Decline Request")
            .setMessage("Are you sure you want to decline this request")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Decline") { dialog, _ ->
                getViewModel().changeRequestStatus(item.workflowCorrelationId, 2, true)
                dialog.dismiss()
            }
            .show()
    }

    override fun onSuccess(leavesResponse: LeavesResponse) {

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
        maxSkip = response.totalCount
        requests.addAll(response.items)
        receivedHistoryRequestsAdapter.setItemsList(requests)
        isLoading = false
    }

    override fun onChangeRequestSuccess() {
        getViewModel().getLeaveRequests(skipCount = 0, maxResult = 2, isManager = true)
    }


}

