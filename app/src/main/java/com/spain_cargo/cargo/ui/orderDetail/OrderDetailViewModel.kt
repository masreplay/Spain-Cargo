package com.spain_cargo.cargo.ui.orderDetail

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.orders.OrderX
import com.spain_cargo.cargo.ui.base.BaseViewModel

class OrderDetailViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IOrderDetailInteractionListener>(
    dataManager
) {

    var orderResponse: MutableLiveData<BaseResource<OrderX>> = MutableLiveData()

    fun getOrderById(id: String) {
        orderResponse.value = BaseResource.loading(orderResponse.value?.data)
        dispose(
            dataManager.getOrderById(id),
            ::onOrderSuccess,
            { e ->
                e.message?.let { orderResponse.postValue(BaseResource.error(it, null)) }
            })
        refreshListener.postValue(View.OnClickListener { getOrderById(id) })
    }

    private fun onOrderSuccess(result: BaseResource<OrderX>) {
        result.data?.let { orderResponse.postValue(result) }
    }
}