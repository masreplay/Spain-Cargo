package com.spain_cargo.cargo.ui.moneyRBCreate
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.requests.MoneyRBResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel

class MoneyRBCreateViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ICreateMoneyRBInteractionListener>(
    dataManager
) {

    var requestMoneyResponse: MutableLiveData<BaseResource<MoneyRBResponse>> =
        MutableLiveData()

    fun moneyBackRequests(from: String, amount: Int) {
        requestMoneyResponse.value = BaseResource.loading(requestMoneyResponse.value?.data)
        dispose(
            dataManager.moneyBackRequests(from, amount),
            ::onRequestSuccess,
            { e ->
                e.message?.let { requestMoneyResponse.postValue(BaseResource.error(it, null)) }
            })
    }

    private fun onRequestSuccess(result: BaseResource<MoneyRBResponse>) {
        result.data?.let { requestMoneyResponse.postValue(result) }
    }


}