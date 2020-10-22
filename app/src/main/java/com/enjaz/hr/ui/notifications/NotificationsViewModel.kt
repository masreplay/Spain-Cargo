package com.enjaz.hr.ui.notifications

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.Data
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel
import com.enjaz.hr.ui.home.IHomeInteractionListener
import com.enjaz.hr.util.PrefsManager

class NotificationsViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<INotificationsInteractionListener>(
    dataManager
) {

    var notificationsResponse: MutableLiveData<MutableList<String>>  =
        MutableLiveData()




    fun getdata(page:Int) {

    notificationsResponse.value= mutableListOf("ali","ahmed","ahmed","ahmed","ahmed","ahmed")

//
//
//
//        dispose(
//            dataManager.getPassengers(page,size),
//            ::onGetNotificationsSuccess,
//            { e ->
//                //error handling
//                run {
//
//
//                    Log.d("Abdalla19977", e.message!!)
//                }
//
//            })
    }

    private fun onGetNotificationsSuccess(result: BaseResource<BaseResponse<Data>>) {


    }

}