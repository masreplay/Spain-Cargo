package com.enjaz.hr.ui.home

import android.util.Log
import android.view.View
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

    fun getHomeData(month:Int,year:Int){

        Log.d("kkkkkk", homeResponse.value?.data?.teammates?.size.toString())

        homeResponse.value = BaseResource.loading(homeResponse.value?.data)



        dispose(
            dataManager.getHome(month,year),
            ::onGetHomeSuccess,
            { e ->
                //error handling
                e.message?.let { homeResponse.postValue(BaseResource.error(it, null)) }


            })
        refreshListener.postValue(View.OnClickListener { getHomeData(month,year) })



    }

    private fun onGetHomeSuccess(result: BaseResource<HomeResponse>) {

        Log.d("hassaneinSuccess",result.data.toString())

        if (result.message !=null){


            Log.d("HassaneinErrorMessage",result.message)


        }else result.data?.let {
            homeResponse.postValue(result)
            Log.d("kkkkkk", homeResponse.value?.data?.teammates?.size.toString())


        }

    }


}