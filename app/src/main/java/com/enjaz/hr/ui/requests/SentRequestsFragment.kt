package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.vvalidator.util.show
import com.enjaz.hr.R
import com.enjaz.hr.data.model.getLeaveRequests.LeaveRequestResponseItem
import com.enjaz.hr.databinding.FramgnetSentRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.sendRequest.SendRequestViewModel
import com.enjaz.hr.util.makeGone
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


        getViewDataBinding().rv.apply {
            adapter = sentRequestsAdapter
        }

        getViewDataBinding().stl.isRefreshing = false

        val lm = LinearLayoutManager(requireActivity())
        getViewDataBinding().rv.layoutManager = lm


//        getViewDataBinding().rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//
//                if (lm.findLastVisibleItemPosition() == lm.itemCount - 1) {
//                    getViewModel().appenddata()
//                    sentRequestsAdapter.notifyDataSetChanged()
//                }
//
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })



        sedRequestViewModel.sendLeaveRequestResponse.observe(requireActivity(), Observer { resource ->
            resource?.data?.let {
                getViewModel().getLeaveRequests(false)
            }
        })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sentRequestsAdapter = SentRequestsAdapter(requireContext(), mutableListOf())
        sentRequestsAdapter.setOnItemClickListener(this)

        getViewModel().getLeaveRequests(false)

    }

    override fun onCancelClick(leaveRequestResponse: LeaveRequestResponseItem) {
        if (leaveRequestResponse.leaveStatus == "WaitingForApproval") {
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle(getString(R.string.casncel_request))
                .setMessage(getString(R.string.msg_cancel))
                .setNegativeButton(getString(R.string.dismiss)) { dialog, which ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                    getViewModel().changeRequestStatus(
                        leaveRequestResponse.workflowCorrelationId,
                        3
                    )
                    dialog.dismiss()
                }
                .show()
        }
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