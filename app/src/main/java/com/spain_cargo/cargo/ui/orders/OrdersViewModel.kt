package com.spain_cargo.cargo.ui.orders

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.brands.BrandsResponse
import com.spain_cargo.cargo.data.model.countries.CountriesResponse
import com.spain_cargo.cargo.data.model.orders.OrdersResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel
import com.spain_cargo.cargo.util.print

class OrdersViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IOrdersInteractionListener>(
    dataManager
) {


    var ordersResponse: MutableLiveData<BaseResource<OrdersResponse>> = MutableLiveData()


    fun getOrders(status:String) {
        ordersResponse.value = BaseResource.loading(ordersResponse.value?.data)

        dispose(
            dataManager.getOrders(status),
            ::onOrdersSuccess,
            { e ->
                //error handling
                e.message?.let {
                    ordersResponse.postValue(BaseResource.error(it, null)) }
            })

        refreshListener.postValue(View.OnClickListener { getOrders(status) })

    }

    private fun onOrdersSuccess(result: BaseResource<OrdersResponse>) {

        result.data?.let {
            ordersResponse.postValue(result)
        }

    }

}