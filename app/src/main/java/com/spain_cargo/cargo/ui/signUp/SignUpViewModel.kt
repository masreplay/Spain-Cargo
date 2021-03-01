package com.spain_cargo.cargo.ui.signUp

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.login.MainResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel
import com.spain_cargo.cargo.util.PrefsManager
import okhttp3.MultipartBody

class SignUpViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ILoginInteractionListener>(
    dataManager
) {


    var signupResponse: MutableLiveData<BaseResource<MainResponse>> = MutableLiveData()


    fun signUp(
        image: MultipartBody.Part, name: String, email: String, password: String,
        phone_number: String, date_of_birth: String
    ) {
        signupResponse.value = BaseResource.loading(signupResponse.value?.data)

        dispose(
            dataManager.signUp(
                image, name, email, password,
                phone_number, date_of_birth
            ),
            ::onSignUpSuccess,
            { e ->
                e.message?.let { signupResponse.postValue(BaseResource.error(it, null)) }
            })
    }

    private fun onSignUpSuccess(result: BaseResource<MainResponse>) {
        signupResponse.value = result

        result.data?.let {
            PrefsManager.instance?.saveUser(it)
        }
    }

}