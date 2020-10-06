package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetSentRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SentRequestsFragment :
    BaseFragment<FramgnetSentRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.framgnet_sent_requests
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

