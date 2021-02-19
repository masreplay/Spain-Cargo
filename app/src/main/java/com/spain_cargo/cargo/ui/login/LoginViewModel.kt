package com.spain_cargo.cargo.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.login.MainResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel
import com.spain_cargo.cargo.util.PrefsManager

class LoginViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ILoginInteractionListener>(
    dataManager
) {

    val email: MutableLiveData<String> = MutableLiveData()
    val pass: MutableLiveData<String> = MutableLiveData()

    var loginResponse: MutableLiveData<BaseResource<MainResponse>> = MutableLiveData()


    fun login() {
        loginResponse.value = BaseResource.loading(loginResponse.value?.data)

        dispose(
            dataManager.login(email.value?.trim().toString(), pass.value?.trim().toString()),
            ::onLoginSuccess,
            { e ->
                //error handling
                e.message?.let { loginResponse.postValue(BaseResource.error(it, null)) }
            })
    }

    private fun onLoginSuccess(result: BaseResource<MainResponse>) {
        loginResponse.value = result

        result.data?.let {
            PrefsManager.instance?.saveUser(it)
        }
    }

}