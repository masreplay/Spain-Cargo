package com.spain_cargo.cargo.ui.requestMoney

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.requestMoney.RequestMoneyResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel

class RequestMoneyViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ICreateOrderInteractionListener>(
    dataManager
) {
    var requestMoneyResponse: MutableLiveData<BaseResource<RequestMoneyResponse>> = MutableLiveData()

    fun moneyRequest(
        from: String,
        amount: Int,
        type: String,
        payment_key: String? = null
    ) {
        requestMoneyResponse.value = BaseResource.loading(requestMoneyResponse.value?.data)
        dispose(
            dataManager.moneyRequest(from, amount, type, payment_key),
            ::onRequestSuccess,
            { e ->
                e.message?.let { requestMoneyResponse.postValue(BaseResource.error(it, null)) }
            })
    }

    private fun onRequestSuccess(result: BaseResource<RequestMoneyResponse>) {
        result.data?.let { requestMoneyResponse.postValue(result) }
    }


}