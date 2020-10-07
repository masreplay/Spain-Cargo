package com.enjaz.hr.ui.requests

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel

class RequestsViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IRequestsInteractionListener>(
    dataManager
) {

    var strings: MutableLiveData<MutableList<String>> =
        MutableLiveData()


    fun getdata() {
        strings.value = mutableListOf("ali", "hussein", "Abbas", "Omar", "hassanein")
    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {

        //loginResponse.postValue(result)
    }

}