package com.spain_cargo.cargo.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.profile.ProfileResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel

class ProfileViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IProfileInteractionListener>(
    dataManager
) {
    var profileResponse: MutableLiveData<BaseResource<ProfileResponse>> = MutableLiveData()

    fun getUser(
       ) {
        profileResponse.value = BaseResource.loading(profileResponse.value?.data)
        dispose(
            dataManager.getUser(),
            ::onRequestSuccess,
            { e ->
                e.message?.let { profileResponse.postValue(BaseResource.error(it, null)) }
            })
    }

    private fun onRequestSuccess(result: BaseResource<ProfileResponse>) {
        result.data?.let { profileResponse.postValue(result) }
    }


}