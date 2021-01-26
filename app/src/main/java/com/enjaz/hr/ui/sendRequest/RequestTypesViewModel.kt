package com.enjaz.hr.ui.sendRequest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.Types
import com.enjaz.hr.ui.base.BaseViewModel

class RequestTypesViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IRequestTypesInteractionListener>(
    dataManager
) {


    var dataResponse: MutableLiveData<MutableList<Types>> = MutableLiveData()


    fun getData() {
        dataResponse.value = mutableListOf(
            Types("Vacation"), Types("Hourly"), Types("Miss Punch"), Types("Overtime")
        )
    }

}


