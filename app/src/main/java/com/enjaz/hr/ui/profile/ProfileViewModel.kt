package com.enjaz.hr.ui.profile

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel
import com.enjaz.hr.ui.home.IHomeInteractionListener
import com.enjaz.hr.util.PrefsManager

class ProfileViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IProfileInteractionListener>(
    dataManager
) {

    var loginResponse: MutableLiveData<BaseResource<BaseResponse<TokenResult>>> =
        MutableLiveData()


    fun mylog() {
//        dispose(
//            dataManager.login("email.value!!.trim()", "pass.value!!.trim()"),
//            ::onLoginSuccess,
//            { e ->
//                //error handling
//                run {
//
//                    pref.getAccessToken()
//
//                    Log.d("Abdalla19977", e.message!!)
//                }
//
//            })
    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {

//        loginResponse.postValue(result)
    }

}