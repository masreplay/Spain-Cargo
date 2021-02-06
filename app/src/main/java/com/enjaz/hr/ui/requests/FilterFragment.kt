package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.data.model.getLeaveRequests.LeavesResponse
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

    override fun onSuccess(leavesResponse: LeavesResponse) {
    }

    override fun noRequests() {
    }

    override fun requestsAvailable() {
    }

    override fun showSnack(string: String, color: String, drawable: Int?) {
    }

    override fun onFabClick() {
    }

    override fun onHistorySuccess(response: LeavesResponse) {
    }

    override fun onChangeRequestSuccess() {

    }

    override fun onShowMoreClicked() {
    }

    override fun onChangeRequestFailure() {
    }

    override fun onChangeRequestNetwork() {

    }


}




