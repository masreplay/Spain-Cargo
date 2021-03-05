package com.spain_cargo.cargo.ui.orders

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.orders.Order
import com.spain_cargo.cargo.data.model.orders.OrdersResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel

class OrdersViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IOrdersInteractionListener>(
    dataManager
) {


    var ordersResponse: MutableLiveData<BaseResource<OrdersResponse>> = MutableLiveData()
    var orderDeleteResponse: MutableLiveData<BaseResource<Order>> = MutableLiveData()
    var orderCompleteResponse: MutableLiveData<BaseResource<Order>> = MutableLiveData()
    var orderRefundResponse: MutableLiveData<BaseResource<Order>> = MutableLiveData()

    fun getOrders(status: String, page: Int) {
        ordersResponse.value = BaseResource.loading(ordersResponse.value?.data)
        dispose(
            dataManager.getOrders(status, page),
            ::onOrdersSuccess,
            { e ->
                e.message?.let { ordersResponse.postValue(BaseResource.error(it, null)) }
            })
        refreshListener.postValue(View.OnClickListener { getOrders(status, page) })
    }

    private fun onOrdersSuccess(result: BaseResource<OrdersResponse>) {
        result.data?.let {
            ordersResponse.postValue(result)
            navigator.onSuccess(result.data)
        }
    }


    fun deleteOrder(id: String) {
        orderDeleteResponse.value = BaseResource.loading(orderDeleteResponse.value?.data)
        dispose(
            dataManager.deleteOrder(id),
            ::onOrderDeleteSuccess,
            { e ->
                e.message?.let { orderDeleteResponse.postValue(BaseResource.error(it, null)) }
            })
    }

    private fun onOrderDeleteSuccess(result: BaseResource<Order>) {
        result.data?.let { orderDeleteResponse.postValue(result) }
    }


    fun markOrderAsRefund(id: String) {
        orderRefundResponse.value = BaseResource.loading(orderRefundResponse.value?.data)
        dispose(
            dataManager.markOrderAsRefund(id),
            ::onMarkOrderAsRefundSuccess,
            { e ->
                e.message?.let { orderRefundResponse.postValue(BaseResource.error(it, null)) }
            })
    }

    private fun onMarkOrderAsRefundSuccess(result: BaseResource<Order>) {
        result.data?.let { orderRefundResponse.postValue(result) }
    }


    fun markOrderAsComplete(id: String) {
        orderCompleteResponse.value = BaseResource.loading(orderCompleteResponse.value?.data)
        dispose(
            dataManager.markOrderAsComplete(id),
            ::onMarkOrderAsCompleteSuccess,
            { e ->
                e.message?.let { orderCompleteResponse.postValue(BaseResource.error(it, null)) }
            })
    }

    private fun onMarkOrderAsCompleteSuccess(result: BaseResource<Order>) {
        result.data?.let { orderCompleteResponse.postValue(result) }
    }
}