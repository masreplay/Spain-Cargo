package com.enjaz.hr.ui.requests

import androidx.hilt.lifecycle.ViewModelInject
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

    /*var loginResponse: MutableLiveData<BaseResource<BaseResponse<TokenResult>>> =
        MutableLiveData()*/


    fun mylog() {

    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {

        //loginResponse.postValue(result)
    }

}