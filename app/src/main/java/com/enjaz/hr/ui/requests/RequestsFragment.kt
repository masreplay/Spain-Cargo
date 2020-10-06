package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestsFragment :
    BaseFragment<FramgnetRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.framgnet_requests
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

interface IRequestsInteractionListener : BaseNavigator {

}
