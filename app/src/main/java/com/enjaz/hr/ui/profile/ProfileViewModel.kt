package com.enjaz.hr.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel

class ProfileViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IProfileInteractionListener>(
    dataManager
) {

    lateinit var loginResponse: ArrayList<String>


    fun mylog() {

        loginResponse= arrayListOf("ahmed","ali","yousif","jjjj")

    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {

//        loginResponse.postValue(result)
    }

}