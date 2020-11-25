package com.enjaz.hr.ui.sentRequest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.Types
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel

class SendRequestViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ISendRequestInteractionListener>(
    dataManager
) {

    var dataResponse: MutableLiveData<MutableList<Types>> =
        MutableLiveData()


    public fun getData() {
        dataResponse.value= mutableListOf(
            Types("Annual leave"),Types("Bereavement leave"),Types("Business trip leave"),
            Types("Long service leave"),Types("Maternity/Paternity leave"),Types( "Sick leave"),
            Types("Self-Quarantine Leave"),Types("Time off in lieu"),Types("Unpaid leave"))


    }

    private fun onLoginSuccess(result: BaseResource<TokenResult>) {

    }

}