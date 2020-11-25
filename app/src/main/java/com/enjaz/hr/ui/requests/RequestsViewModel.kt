package com.enjaz.hr.ui.requests

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.R
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.Request
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel

class RequestsViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IRequestsInteractionListener>(
    dataManager
) {

    var strings: MutableLiveData<MutableList<Request>> =
        MutableLiveData()


    fun getdata() {
        strings.value = mutableListOf(
            Request("Pending", 1, R.color.orange),
            Request("Approved", 2, R.color.green_400),
            Request("Rejected", 3, R.color.red),
            Request("Rejected", 3, R.color.red),
            Request("Rejected", 3, R.color.red)

        )
    }

    fun appenddata() {
        strings.value?.add(Request("Pending", 1, R.color.orange))
        strings.value?.add(Request("Approved", 2, R.color.green_400))
        strings.value?.add(Request("Rejected", 3, R.color.red))

        strings.value = strings.value
    }


    private fun onLoginSuccess(result: BaseResource<TokenResult>) {

        //loginResponse.postValue(result)
    }

}