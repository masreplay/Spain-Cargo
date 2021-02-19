package com.spain_cargo.cargo.ui.countries

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.countries.CountriesResponse
import com.spain_cargo.cargo.data.model.login.MainResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel
import com.spain_cargo.cargo.util.PrefsManager
import com.spain_cargo.cargo.util.print

class CountriesViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ICountriesInteractionListener>(
    dataManager
) {


    var countriesResponse: MutableLiveData<BaseResource<CountriesResponse>> = MutableLiveData()


    fun getCountries() {
        countriesResponse.value = BaseResource.loading(countriesResponse.value?.data)

        dispose(
            dataManager.getCountries(),
            ::onCountriesSuccess,
            { e ->
                //error handling
                e.message?.let {
                    countriesResponse.postValue(BaseResource.error(it, null)) }
            })

        refreshListener.postValue(View.OnClickListener { getCountries() })

    }

    private fun onCountriesSuccess(result: BaseResource<CountriesResponse>) {

        result.data?.let {
            countriesResponse.postValue(result)
        }

    }

}