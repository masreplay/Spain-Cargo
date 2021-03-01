package com.spain_cargo.cargo.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.profile.ProfileResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel
import com.spain_cargo.cargo.util.PrefsManager

class ProfileUpdateViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IProfileUpdateInteractionListener>(
    dataManager
) {

    var updateProfileResponse: MutableLiveData<BaseResource<ProfileResponse>> = MutableLiveData()


    fun updateProfile(
        name: String, email: String, phone_number: String, date_of_birth: String
    ) {
        updateProfileResponse.value = BaseResource.loading(updateProfileResponse.value?.data)
        dispose(
            dataManager.updateProfile(name, email, phone_number, date_of_birth),
            ::onUpdateProfileSuccess,
            { e ->
                e.message?.let { updateProfileResponse.postValue(BaseResource.error(it, null)) }
            }
        )
    }

    private fun onUpdateProfileSuccess(result: BaseResource<ProfileResponse>) {
        updateProfileResponse.value = result
        result.data?.let {
            PrefsManager.instance?.saveProfile(it)
        }
    }
}