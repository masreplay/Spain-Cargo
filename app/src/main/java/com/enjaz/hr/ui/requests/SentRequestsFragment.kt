package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetSentRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SentRequestsFragment :
    BaseFragment<FramgnetSentRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener, ISRequestsItemActionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()

    lateinit var sentRequestsAdapter: SentRequestsAdapter


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





        getViewModel().getdata()
        getViewDataBinding().rv.apply {
            adapter = sentRequestsAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sentRequestsAdapter = SentRequestsAdapter(requireContext(), mutableListOf())
        sentRequestsAdapter.setOnItemClickListener(this)

    }

    override fun onCancelClick() {
        MaterialAlertDialogBuilder(context)
            .setTitle("Cancel Request")
            .setMessage("Are you sure you want to cancel this request")
            .setNegativeButton("dismiss") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("ok") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }


}

