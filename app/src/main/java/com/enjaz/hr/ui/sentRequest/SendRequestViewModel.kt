package com.enjaz.hr.ui.sentRequest

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.R
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.requestsTypes.RequestTypesResponse
import com.enjaz.hr.data.model.sendRequest.SendRequestResponse
import com.enjaz.hr.ui.base.BaseViewModel

class SendRequestViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ISendRequestInteractionListener>(
    dataManager
) {



    var leaveTypesResponse: MutableLiveData<BaseResource<RequestTypesResponse>> = MutableLiveData()
    var sendLeaveRequestResponse: MutableLiveData<BaseResource<SendRequestResponse>> = MutableLiveData()


    fun getLeaveTypes(timeFlag:Boolean){


        leaveTypesResponse.value = BaseResource.loading(leaveTypesResponse.value?.data)



        dispose(
            dataManager.getLeaveTypes(timeFlag),
            ::onGetLeaveTypesSuccess,
            { e ->
                //error handling
                e.message?.let { leaveTypesResponse.postValue(BaseResource.error(it, null))
                    Log.d("error",it)
                }


            })
        refreshListener.postValue(View.OnClickListener { getLeaveTypes(timeFlag) })



    }

    fun sendLeaveRequest(startDate:String,endDate:String,leaveName:String,leaveDescription:String,leaveTypeId:Int){



        navigator.onSendingRequest()


        dispose(
            dataManager.sendLeaveRequest(startDate,endDate,leaveName,leaveDescription,leaveTypeId),
            ::onSendLeaveRequestSuccess,
            { e ->
                //error handling
                e.message?.let {
                    sendLeaveRequestResponse.postValue(BaseResource.error(it, null))
                    Log.d("error",it)
                }


            })



    }
    private fun onGetLeaveTypesSuccess(result: BaseResource<RequestTypesResponse>) {

        leaveTypesResponse.postValue(result)

        if (result.message !=null){


            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)


        }else result.data?.let {



//            if (it.days.isEmpty()){
//                navigator.noAttendance()
//            }else{
//                navigator.attendanceAvailable()
//
//            }

        }

    }

    private fun onSendLeaveRequestSuccess(result: BaseResource<SendRequestResponse>) {

        sendLeaveRequestResponse.postValue(result)

        if (result.message !=null){

            navigator.onSendingRequestError(result.message)



        }else result.data?.let {



            navigator.onSendingRequestSuccess()
//            if (it.days.isEmpty()){
//                navigator.noAttendance()
//            }else{
//                navigator.attendanceAvailable()
//
//            }

        }

    }




}