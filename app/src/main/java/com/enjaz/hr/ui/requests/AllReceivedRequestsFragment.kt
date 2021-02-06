package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.enjaz.hr.R
import com.enjaz.hr.data.model.getLeaveRequests.Item
import com.enjaz.hr.data.model.getLeaveRequests.LeavesResponse
import com.enjaz.hr.databinding.FramgnetAllReceivedRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.util.Constants
import com.enjaz.hr.util.snackBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllReceivedRequestsFragment :
    BaseFragment<FramgnetAllReceivedRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener, IRAllRequestsItemActionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()
    lateinit var loadingDialog: SweetAlertDialog
    private var deletedItemPosition: Int = 0
    private var requestType: Int = 0


    private lateinit var receiveRequestsAdapter: AllReceivedRequestsAdapter
    var requests = mutableListOf<Item>()
    private var maxSkip: Int = 0
    var skip: Int = 0
    var isLoading = false


    override fun getLayoutId(): Int {
        return R.layout.framgnet_all_received_requests
    }

    override fun getViewModel(): RequestsViewModel {
        return requestsViewModel
    }


    override fun getNavigator(): IRequestsInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loadingDialog = SweetAlertDialog(requireActivity(), SweetAlertDialog.PROGRESS_TYPE)


        val lm = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        getViewDataBinding().rv.apply {
            adapter = receiveRequestsAdapter
            layoutManager = lm
        }

        getViewDataBinding().rv.addOnScrollListener(object :
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

                    getViewModel().getLeaveRequests(skipCount = skip, isManager = true)


                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveRequestsAdapter =
            AllReceivedRequestsAdapter(requireContext(), requests)
        receiveRequestsAdapter.setOnItemClickListener(this)
        receiveRequestsAdapter.setHasStableIds(true)

        getViewModel().getLeaveRequests(skipCount = skip, isManager = true)


    }

    override fun onAcceptClick(item: Item, position: Int) {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Accept Request")
            .setMessage("Are you sure you want to accept this request")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Accept") { dialog, _ ->
                getViewModel().changeRequestStatus(item.workflowCorrelationId, 1, true)
                loadingDialog.titleText = "Accepting request"
                loadingDialog.show()
                deletedItemPosition = position
                requestType = 1
                dialog.dismiss()
            }
            .show()
    }

    override fun onDeclinedClick(item: Item, position: Int) {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Decline Request")
            .setMessage("Are you sure you want to decline this request")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Decline") { dialog, _ ->
                getViewModel().changeRequestStatus(item.workflowCorrelationId, 2, true)
                loadingDialog.titleText = "Declining request"
                loadingDialog.show()
                deletedItemPosition = position
                requestType = 2
                dialog.dismiss()
            }
            .show()
    }

    override fun onSuccess(leavesResponse: LeavesResponse) {
        maxSkip = leavesResponse.totalCount
        requests.addAll(leavesResponse.items)
        receiveRequestsAdapter.setItemsList(requests)
        isLoading = false
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
        val res = requests
        res.removeAt(deletedItemPosition)
        requests = res
        receiveRequestsAdapter.notifyDataSetChanged()
        loadingDialog.hide()
        if (requestType == 1)
            Toast.makeText(requireActivity(), "Request Accepted Successfully", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(requireActivity(), "Request Declined Successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onShowMoreClicked() {

    }

    override fun onChangeRequestFailure() {
        loadingDialog.hide()
    }

    override fun onChangeRequestNetwork() {
        loadingDialog.hide()
    }


}

