package com.enjaz.hr.ui.onboarding

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel

class OnboardingViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IOnboardingInteractionListener>(
    dataManager
) {


    var tokenResponse: MutableLiveData<BaseResource<TokenResult>> = MutableLiveData()


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

    private fun onLoginSuccess(result: BaseResource<TokenResult>?) {
        tokenResponse.value = result

        if (result?.message != null) {

//            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_close)

        }

        result?.data?.let {

        }
    }

}