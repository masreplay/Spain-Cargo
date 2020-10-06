package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetHomeBinding
import com.enjaz.hr.databinding.FramgnetReceivedRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceivedRequestsFragment :
    BaseFragment<FramgnetReceivedRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.framgnet_received_requests
    }

    override fun getViewModel(): RequestsViewModel {
        return requestsViewModel
    }

    override fun getViewModelClass(): Class<RequestsViewModel> {
        return RequestsViewModel::class.java
    }

    override fun getNavigator(): IRequestsInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().mylog()
    }


}

