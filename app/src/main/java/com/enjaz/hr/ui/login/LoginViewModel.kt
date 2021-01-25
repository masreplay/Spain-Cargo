package com.enjaz.hr.ui.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.Status
import com.enjaz.hr.data.model.login.LoginResponse
import com.enjaz.hr.ui.base.BaseViewModel
import com.enjaz.hr.util.PrefsManager

class LoginViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ILoginInteractionListener>(
    dataManager
) {

    val email: MutableLiveData<String> = MutableLiveData()
    val pass: MutableLiveData<String> = MutableLiveData()

    var tokenResponse: MutableLiveData<BaseResource<LoginResponse>> = MutableLiveData()


    fun login() {
        tokenResponse.value = BaseResource.loading(tokenResponse.value?.data)

        dispose(
            dataManager.login(email.value?.trim().toString(), pass.value?.trim().toString()),
            ::onLoginSuccess,
            { e ->
                //error handling
                e.message?.let { tokenResponse.postValue(BaseResource.error(it, null)) }
            })
    }

    private fun onLoginSuccess(result: BaseResource<LoginResponse>) {
        tokenResponse.value = result



        result.data?.let {
            PrefsManager.instance?.saveAccessToken(it.accessToken)
            navigator.login()
        }
    }

}