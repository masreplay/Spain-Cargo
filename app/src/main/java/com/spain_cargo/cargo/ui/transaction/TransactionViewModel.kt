package com.spain_cargo.cargo.ui.transaction

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.profile.ProfileResponse
import com.spain_cargo.cargo.data.model.transaction.TransactionResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel
import com.spain_cargo.cargo.util.PrefsManager

class TransactionViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ITransactionInteractionListener>(
    dataManager
) {
    var transactionResponse: MutableLiveData<BaseResource<TransactionResponse>> = MutableLiveData()

    fun getTransaction() {
        transactionResponse.value = BaseResource.loading(transactionResponse.value?.data)
        dispose(
            dataManager.getTransaction(), ::onTransactionSuccess,
            { e -> e.message?.let { transactionResponse.postValue(BaseResource.error(it, null)) } }
        )
    }

    private fun onTransactionSuccess(result: BaseResource<TransactionResponse>) {
        transactionResponse.value = result
    }

}