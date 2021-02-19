package com.spain_cargo.cargo.ui.createOrder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.CreateOrder
import com.spain_cargo.cargo.data.model.Item
import com.spain_cargo.cargo.data.model.cities.CitiesResponse
import com.spain_cargo.cargo.data.model.countries.CountriesResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel
import com.spain_cargo.cargo.util.print

class CreateOrderViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ICreateOrderInteractionListener>(
    dataManager
) {


    var citiesResponse: MutableLiveData<BaseResource<CitiesResponse>> = MutableLiveData()

    var checkoutResponse: MutableLiveData<BaseResource<String>> = MutableLiveData()


    var itemsResponse: MutableLiveData<List<Item>> = MutableLiveData()


    fun getCities() {
        citiesResponse.value = BaseResource.loading(citiesResponse.value?.data)

        dispose(
            dataManager.getCities(),
            ::onCitiesSuccess,
            { e ->
                //error handling
                e.message?.let {
                    citiesResponse.postValue(BaseResource.error(it, null))
                }
            })


    }

    fun checkout(order:CreateOrder) {
        checkoutResponse.value = BaseResource.loading(checkoutResponse.value?.data)

        dispose(
            dataManager.checkout(order),
            ::onOrderSuccess,
            { e ->
                //error handling
                e.message?.let {
                    checkoutResponse.postValue(BaseResource.error(it, null))
                }
            })


    }



    fun deleteItem(item: Item) {
        dataManager.deleteItem(item)
    }

    fun getItems() {
        dataManager.getItems().subscribe ({ items->
            itemsResponse.postValue(items)
        },{

        })
    }

    private fun onCitiesSuccess(result: BaseResource<CitiesResponse>) {

        result.data?.let {
            citiesResponse.postValue(result)
        }

    }

    private fun onOrderSuccess(result: BaseResource<String>) {

        result.data?.let {
            checkoutResponse.postValue(result)
        }

    }

}