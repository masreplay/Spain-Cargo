package com.enjaz.hr.ui.home

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.R
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.home.HomeResponse
import com.enjaz.hr.ui.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IHomeInteractionListener>(
    dataManager
) {

    var homeResponse: MutableLiveData<BaseResource<HomeResponse>> = MutableLiveData()

    fun getHomeData(month:Int,year:Int){

        homeResponse.value = BaseResource.loading(homeResponse.value?.data)

        dispose(
            dataManager.getHome(month,year),
            ::onGetHomeSuccess,
            { e ->
                //error handling
                e.message?.let { homeResponse.postValue(BaseResource.error(it, null))
                    Log.d("error",it)
                }

            })
        refreshListener.postValue(View.OnClickListener { getHomeData(month,year) })



    }

    private fun onGetHomeSuccess(result: BaseResource<HomeResponse>) {

        result.message?.let{
            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)
        }
        result.data?.let {
            homeResponse.postValue(result)
        }

    }


}