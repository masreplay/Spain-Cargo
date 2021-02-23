package com.spain_cargo.cargo.ui.moneyRB

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.requests.MoneyRBResponseArray
import com.spain_cargo.cargo.ui.base.BaseViewModel

class MoneyRBViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IMoneyRBInteractionListener>(
    dataManager
) {


    var moneyBRResponse: MutableLiveData<BaseResource<MoneyRBResponseArray>> = MutableLiveData()
    var moneyBRAcceptedResponse: MutableLiveData<BaseResource<String>> = MutableLiveData()
    var moneyBRRejectedResponse: MutableLiveData<BaseResource<String>> = MutableLiveData()

    fun getMoneyRB() {
        moneyBRResponse.value = BaseResource.loading(moneyBRResponse.value?.data)
        dispose(
            dataManager.getMoneyRB(), ::onMoneyRBSuccess,
            { e ->
                e.message?.let { moneyBRResponse.postValue(BaseResource.error(it, null)) }
            })
        refreshListener.postValue(View.OnClickListener { getMoneyRB() })
    }

    private fun onMoneyRBSuccess(result: BaseResource<MoneyRBResponseArray>) {
        result.data?.let { moneyBRResponse.postValue(result) }
    }

    fun markMoneyBrAsAccepted(id: Int) {
        moneyBRAcceptedResponse.value = BaseResource.loading(moneyBRAcceptedResponse.value?.data)
        dispose(
            dataManager.markMoneyBrAsAccepted(id), ::onMoneyRBAcceptedSuccess,
            { e ->
                e.message?.let { moneyBRAcceptedResponse.postValue(BaseResource.error(it, null)) }
            })
        refreshListener.postValue(View.OnClickListener { markMoneyBrAsAccepted(id) })
    }

    private fun onMoneyRBAcceptedSuccess(result: BaseResource<String>) {
        result.data?.let { moneyBRAcceptedResponse.postValue(result) }
    }

    fun markMoneyBrAsRejected(id: Int) {
        moneyBRRejectedResponse.value = BaseResource.loading(moneyBRRejectedResponse.value?.data)
        dispose(
            dataManager.markMoneyBrAsRejected(id), ::onMoneyRBRejectedSuccess,
            { e ->
                e.message?.let { moneyBRRejectedResponse.postValue(BaseResource.error(it, null)) }
            })
        refreshListener.postValue(View.OnClickListener { markMoneyBrAsRejected(id) })
    }

    private fun onMoneyRBRejectedSuccess(result: BaseResource<String>) {
        result.data?.let { moneyBRRejectedResponse.postValue(result) }
    }


}