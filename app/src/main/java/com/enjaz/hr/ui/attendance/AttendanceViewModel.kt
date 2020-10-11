package com.enjaz.hr.ui.attendance

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel
import com.enjaz.hr.ui.home.IHomeInteractionListener
import com.enjaz.hr.util.PrefsManager

class AttendanceViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IAttendanceInteractionListener>(
    dataManager
) {

    var attendanceResponse: MutableLiveData<MutableList<String>> =
        MutableLiveData()


    fun getdata() {


        attendanceResponse.value= mutableListOf("ali","hussein","Abbas","Omar","hassanein")

//        dispose(
//            dataManager.login("email.value!!.trim()", "pass.value!!.trim()"),
//            ::onLoginSuccess,
//            { e ->
//                //error handling
//                run {
//
//                    pref.getAccessToken()
//
//                    Log.d("Abdalla19977", e.message!!)
//                }
//
//            })
    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {

    }

}