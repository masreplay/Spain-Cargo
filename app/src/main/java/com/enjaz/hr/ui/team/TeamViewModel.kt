package com.enjaz.hr.ui.team

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel

class TeamViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ITeamInteractionListener>(
    dataManager
) {

    var strings: MutableLiveData<MutableList<String>> =
        MutableLiveData()


    fun getdata() {
        strings.value = mutableListOf("ali", "hussein", "Abbas", "Omar", "hassanein")
    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {
    }

}