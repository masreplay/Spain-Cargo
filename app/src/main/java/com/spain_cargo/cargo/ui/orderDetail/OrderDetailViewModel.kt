package com.spain_cargo.cargo.ui.orderDetail

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.orders.Order
import com.spain_cargo.cargo.ui.base.BaseViewModel

class OrderDetailViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IShowOrderInteractionListener>(
    dataManager
) {

    var orderResponse: MutableLiveData<BaseResource<Order>> = MutableLiveData()

    fun getOrder(id: String) {
        orderResponse.value = BaseResource.loading(orderResponse.value?.data)
        dispose(
            dataManager.getOrderById(id),
            ::onOrderSuccess,
            { e ->
                e.message?.let { orderResponse.postValue(BaseResource.error(it, null)) }
            })
        refreshListener.postValue(View.OnClickListener { dataManager.getOrderById(id) })
    }

    private fun onOrderSuccess(result: BaseResource<Order>) {
        result.data?.let { orderResponse.postValue(result) }
    }
}