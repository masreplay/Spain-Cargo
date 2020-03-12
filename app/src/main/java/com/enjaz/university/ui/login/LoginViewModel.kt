package com.enjaz.university.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.enjaz.university.data.AppDataManager
import com.enjaz.university.data.model.BaseResource
import com.enjaz.university.data.model.BaseResponse
import com.enjaz.university.data.model.token.TokenResult
import com.enjaz.university.ui.base.BaseViewModel
import com.enjaz.university.util.PrefsManager

class LoginViewModel (dataManager: AppDataManager): BaseViewModel<ILoginInteractionListener>(dataManager) {

    val email: MutableLiveData<String> = MutableLiveData()
    val pass: MutableLiveData<String> = MutableLiveData()
    var tokenResponse: MutableLiveData<BaseResource<BaseResponse<TokenResult>>> = MutableLiveData()

    fun login(){
        dispose(dataManager.login(email.value?:"",pass.value?:""),::onLoginSuccess,{e->
            //error handling
        })
    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>){
        tokenResponse.value=result
        result.data?.let {
            PrefsManager.instance?.saveTokens( result.data.result)
            navigator.login()
        }
    }
}