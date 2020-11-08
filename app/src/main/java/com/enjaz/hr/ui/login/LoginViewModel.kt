package com.enjaz.hr.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel
import com.enjaz.hr.ui.home.IHomeInteractionListener
import com.enjaz.hr.util.PrefsManager

class LoginViewModel @ViewModelInject constructor(
    dataManager: AppDataManager,
    var pref: PrefsManager
) : BaseViewModel<ILoginInteractionListener>(
    dataManager
) {

    val email: MutableLiveData<String> = MutableLiveData()
    val pass: MutableLiveData<String> = MutableLiveData()
    val tenancy: MutableLiveData<String> = MutableLiveData()

    var tokenResponse: MutableLiveData<BaseResource<BaseResponse<TokenResult>>> = MutableLiveData()


    fun login() {
        tokenResponse.value = BaseResource.loading(tokenResponse.value?.data)

//        dispose(
//            dataManager.login(
//                email.value?.trim().toString(), pass.value?.trim().toString(),
//                tenancy.value?.trim().toString()
//            ),
//            ::onLoginSuccess,
//            { e ->
//                //error handling
//
//                e.message?.let { tokenResponse.postValue(BaseResource.error(it, null)) }
//            })
    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>?) {
        tokenResponse.value = result

        if (result?.message != null) {

//            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_close)

        }

        result?.data?.let {


            pref.saveTokens(result.data.result)




            navigator.login()
        }
    }

}