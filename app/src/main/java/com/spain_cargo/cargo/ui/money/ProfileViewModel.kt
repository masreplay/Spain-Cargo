package com.spain_cargo.cargo.ui.money

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.profile.ProfileResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel
import com.spain_cargo.cargo.util.print

class ProfileViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IProfileInteractionListener>(
    dataManager
) {


    var usersResponse: MutableLiveData<BaseResource<ProfileResponse>> = MutableLiveData()


    fun getUsers() {
        usersResponse.value = BaseResource.loading(usersResponse.value?.data)

        dispose(
            dataManager.getUser(),
            ::onUsersSuccess,
            { e ->
                //error handling
                e.message?.let {
                    usersResponse.postValue(BaseResource.error(it, null))
                }
            })

        refreshListener.postValue(View.OnClickListener { getUsers() })

    }

    private fun onUsersSuccess(result: BaseResource<ProfileResponse>) {

        result.data?.let {
            usersResponse.postValue(result)
            it.print()

        }

    }

}