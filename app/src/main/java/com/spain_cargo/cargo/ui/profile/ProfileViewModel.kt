package com.spain_cargo.cargo.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.profile.ProfileResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel
import com.spain_cargo.cargo.util.PrefsManager
import okhttp3.MultipartBody

class ProfileViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IProfileInteractionListener>(
    dataManager
) {
    var profileResponse: MutableLiveData<BaseResource<ProfileResponse>> = MutableLiveData()
    var logoutResponse: MutableLiveData<BaseResource<Void>> = MutableLiveData()

    fun getProfile() {
        profileResponse.value = BaseResource.loading(profileResponse.value?.data)
        dispose(
            dataManager.getProfile(), ::onRequestSuccess,
            { e -> e.message?.let { profileResponse.postValue(BaseResource.error(it, null)) } }
        )
    }

    private fun onRequestSuccess(result: BaseResource<ProfileResponse>) {
        profileResponse.value = result
        result.data?.let {
            PrefsManager.instance?.saveProfile(it)
        }
    }

    fun logout() {
        logoutResponse.value = BaseResource.loading(logoutResponse.value?.data)
        dispose(
            dataManager.logout(), ::onLogoutSuccess,
            { e -> e.message?.let { logoutResponse.postValue(BaseResource.error(it, null)) } }
        )
    }

    private fun onLogoutSuccess(result: BaseResource<Void>) {
        result.data?.let { logoutResponse.postValue(result) }
    }


}