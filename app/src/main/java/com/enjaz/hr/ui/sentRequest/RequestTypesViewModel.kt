package com.enjaz.hr.ui.sentRequest

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.R
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.Types
import com.enjaz.hr.data.model.leaveTypes.LeaveTypesResponse
import com.enjaz.hr.ui.base.BaseViewModel

class RequestTypesViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ISendRequestInteractionListener>(
    dataManager
) {


    var dataResponse: MutableLiveData<MutableList<Types>> =MutableLiveData()
    var leaveTypesResponse: MutableLiveData<BaseResource<LeaveTypesResponse>> = MutableLiveData()


    fun getData() {
        dataResponse.value= mutableListOf(
            Types("Vacation"),Types("Hourly"),Types("Overtime"),Types("Miss punch"))


    }
    fun getLeaveTypes(){


        leaveTypesResponse.value = BaseResource.loading(leaveTypesResponse.value?.data)



        dispose(
            dataManager.getLeaveTypes(),
            ::onGetLeaveTypesSuccess,
            { e ->
                //error handling
                e.message?.let { leaveTypesResponse.postValue(BaseResource.error(it, null))
                    Log.d("error",it)
                }


            })
        refreshListener.postValue(View.OnClickListener { getLeaveTypes() })



    }

    private fun onGetLeaveTypesSuccess(result: BaseResource<LeaveTypesResponse>) {


        if (result.message !=null){


            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)


        }else result.data?.let {


            leaveTypesResponse.postValue(result)

//            if (it.days.isEmpty()){
//                navigator.noAttendance()
//            }else{
//                navigator.attendanceAvailable()
//
//            }

        }

    }


}


