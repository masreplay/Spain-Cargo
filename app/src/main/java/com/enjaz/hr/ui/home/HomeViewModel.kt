package com.enjaz.hr.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel
import com.enjaz.hr.util.PrefsManager

class HomeViewModel @ViewModelInject constructor(
    dataManager: AppDataManager,
    var pref: PrefsManager

) : BaseViewModel<IHomeInteractionListener>(
    dataManager
) {

    var strings: MutableLiveData<MutableList<String>> =
        MutableLiveData()

    var dates: MutableLiveData<MutableList<String>> =
        MutableLiveData()

    fun getdata() {
        strings.value = mutableListOf("abd", "hussein")
        dates.value = mutableListOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec")

    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {

        //loginResponse.postValue(result)
    }


}