package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentRequestsFilterBinding
import com.enjaz.hr.ui.base.BaseSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment :
    BaseSheetFragment<FragmentRequestsFilterBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestFilterViewModel: RequestsViewModel by viewModels()

    override fun getLayoutId(): Int {
        return R.layout.fragment_requests_filter
    }

    override fun getViewModel(): RequestsViewModel {
        return requestFilterViewModel
    }

    override fun getNavigator(): IRequestsInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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

    override fun onFabClick() {
        TODO("Not yet implemented")
    }


}




