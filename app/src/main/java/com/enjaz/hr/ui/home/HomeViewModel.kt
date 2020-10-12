package com.enjaz.hr.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel
import com.enjaz.hr.util.PrefsManager

class HomeViewModel @ViewModelInject constructor(
    dataManager: AppDataManager,
    var pref: PrefsManager
) : BaseViewModel<IHomeInteractionListener>(
    dataManager
) {

    var loginResponse: MutableLiveData<BaseResource<BaseResponse<TokenResult>>> =
        MutableLiveData()


    fun mylog() {
        dispose(
            dataManager.login("email.value!!.trim()", "pass.value!!.trim()"),
            ::onLoginSuccess,
            { e ->

                loginResponse.postValue(BaseResource.empty())

            })


    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {

        loginResponse.postValue(BaseResource.empty())
    }

}