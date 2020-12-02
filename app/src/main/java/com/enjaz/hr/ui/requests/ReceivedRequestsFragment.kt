package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetReceivedRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
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



//        getViewModel().getdata()
        getViewDataBinding().rv.apply {
            adapter = receiveRequestsAdapter
        }

        val lm = LinearLayoutManager(requireActivity())
        getViewDataBinding().rv.layoutManager = lm


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

    }

    override fun onAcceptClick() {
        MaterialAlertDialogBuilder(context)
            .setTitle("Accept Request")
            .setMessage("Are you sure you want to accept this request")
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Accept") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDeclinedClick() {
        MaterialAlertDialogBuilder(context)
            .setTitle("Decline Request")
            .setMessage("Are you sure you want to decline this request")
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Decline") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    override fun noRequests() {
        TODO("Not yet implemented")
    }

    override fun requestsAvailable() {
        TODO("Not yet implemented")
    }

    override fun showSnack(string: String, color: String, drawable: Int?) {
        TODO("Not yet implemented")
    }


}

