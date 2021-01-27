package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.vvalidator.util.show
import com.enjaz.hr.R
import com.enjaz.hr.data.model.getLeaveRequests.LeaveRequestResponseItem
import com.enjaz.hr.databinding.FramgnetReceivedRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.util.makeGone
import com.enjaz.hr.util.snackBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceivedRequestsFragment :
    BaseFragment<FramgnetReceivedRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener, IRRequestsItemActionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()

    private lateinit var receiveRequestsAdapter: ReceivedRequestsAdapter


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




        getViewDataBinding().rv.apply {
            adapter = receiveRequestsAdapter
            layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        }



//        getViewDataBinding().rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//
//                if (lm.findLastVisibleItemPosition() == lm.itemCount - 1) {
////                    getViewModel().appenddata()
////                    receiveRequestsAdapter.notifyDataSetChanged()
//                }
//
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveRequestsAdapter = ReceivedRequestsAdapter(requireContext(), mutableListOf())
        receiveRequestsAdapter.setOnItemClickListener(this)

        getViewModel().getLeaveRequests(true)

    }

    override fun onAcceptClick(item: LeaveRequestResponseItem) {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Accept Request")
            .setMessage("Are you sure you want to accept this request")
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Accept") { dialog, which ->
                getViewModel().changeRequestStatus(item.workflowCorrelationId,1)
                dialog.dismiss()
            }
            .show()
    }

    override fun onDeclinedClick(item:LeaveRequestResponseItem) {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Decline Request")
            .setMessage("Are you sure you want to decline this request")
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Decline") { dialog, which ->
                getViewModel().changeRequestStatus(item.workflowCorrelationId,2)
                dialog.dismiss()
            }
            .show()
    }


    override fun noRequests() {
        getViewDataBinding().lytNoData.show()
    }

    override fun requestsAvailable() {
        getViewDataBinding().lytNoData.makeGone()
    }


    override fun showSnack(string: String, color: String, drawable: Int?) {
        snackBar(string, drawable, color, getViewDataBinding().parent, requireContext())

    }

    override fun onFabClick() {
        TODO("Not yet implemented")
    }


}

