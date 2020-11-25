package com.enjaz.hr.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
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

    var dates: MutableLiveData<MutableList<String>> =
        MutableLiveData()

    fun getHomeData(){


        homeResponse.value = BaseResource.loading(homeResponse.value?.data)



        dispose(
            dataManager.getHome(11,2020),
            ::onGetHomeSuccess,
            { e ->
                //error handling
                e.message?.let { homeResponse.postValue(BaseResource.error(it, null)) }


            })


    }

    private fun onGetHomeSuccess(result: BaseResource<HomeResponse>) {

        Log.d("hassaneinSuccess",result.data.toString())

        if (result.message !=null){


            Log.d("HassaneinErrorMessage",result.message)


        }else result.data?.let {
            homeResponse.postValue(result)

        }

    }


}