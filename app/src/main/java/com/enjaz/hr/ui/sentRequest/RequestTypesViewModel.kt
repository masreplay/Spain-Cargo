package com.enjaz.hr.ui.sentRequest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.Types
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel

class RequestTypesViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ISendRequestInteractionListener>(
    dataManager
) {

    var dataResponse: MutableLiveData<MutableList<Types>> =
        MutableLiveData()


     fun getData() {
        dataResponse.value= mutableListOf(
            Types("Vacation"),Types("Hourly"),Types("Overtime"),Types("Miss punch"))


    }


    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {

    }

}


