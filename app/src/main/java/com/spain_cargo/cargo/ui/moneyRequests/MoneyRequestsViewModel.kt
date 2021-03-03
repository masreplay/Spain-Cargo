package com.spain_cargo.cargo.ui.moneyRequests

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.moneyRequests.MoneyRequests
import com.spain_cargo.cargo.ui.base.BaseViewModel

class MoneyRequestsViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IMoneyRequestsInteractionListener>(
    dataManager
) {


    var moneyRequestResponse: MutableLiveData<BaseResource<MoneyRequests>> = MutableLiveData()
    var acceptRequestResponse: MutableLiveData<BaseResource<Void>> = MutableLiveData()
    var declineRequestResponse: MutableLiveData<BaseResource<Void>> = MutableLiveData()


    fun getMoneyRequests(page: Int) {
        moneyRequestResponse.value = BaseResource.loading(moneyRequestResponse.value?.data)

        dispose(
            dataManager.getMoneyRequests(page),
            ::onRequestsSuccess,
            { e ->
                //error handling
                e.message?.let {
                    moneyRequestResponse.postValue(BaseResource.error(it, null))
                }
            })

        refreshListener.postValue(View.OnClickListener { getMoneyRequests(page) })

    }

    fun acceptMoneyRequest(id: String) {
        acceptRequestResponse.value = BaseResource.loading(acceptRequestResponse.value?.data)

        dispose(
            dataManager.acceptMoneyRequest(id),
            ::onRequestsAcceptSuccess,
            { e ->
                //error handling
                e.message?.let {
                    acceptRequestResponse.postValue(BaseResource.error(it, null))
                }
            })

    }

    fun rejectMoneyRequest(id: String) {
        declineRequestResponse.value = BaseResource.loading(declineRequestResponse.value?.data)

        dispose(
            dataManager.rejectMoneyRequest(id),
            ::onRequestsDeclineSuccess,
            { e ->
                //error handling
                e.message?.let {
                    declineRequestResponse.postValue(BaseResource.error(it, null))
                }
            })
    }

    private fun onRequestsSuccess(result: BaseResource<MoneyRequests>) {
        result.data?.let {
            moneyRequestResponse.postValue(result)
            navigator.onSuccess(result.data)
        }
    }

    private fun onRequestsAcceptSuccess(result: BaseResource<Void>) {
    }

    private fun onRequestsDeclineSuccess(result: BaseResource<Void>) {
    }


}