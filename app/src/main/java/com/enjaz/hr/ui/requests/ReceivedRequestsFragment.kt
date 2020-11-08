package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetReceivedRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceivedRequestsFragment :
    BaseFragment<FramgnetReceivedRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()

    lateinit var receiveRequestsAdapter: ReceivedRequestsAdapter


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
        Log.i("abdalla19988", "abdalla19988")
        getViewModel().getdata()
        getViewDataBinding().rv.apply {
            adapter = receiveRequestsAdapter
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("abdalla19988", "abdalla19988")

        receiveRequestsAdapter = ReceivedRequestsAdapter(requireContext(), mutableListOf())
    }


}

