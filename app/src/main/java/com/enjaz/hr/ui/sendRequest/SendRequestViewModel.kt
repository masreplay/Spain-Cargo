package com.enjaz.hr.ui.sendRequest

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
    var sendLeaveRequestResponse: MutableLiveData<BaseResource<SendRequestResponse>> =
        MutableLiveData()

    var missPunchResponse: MutableLiveData<BaseResource<Void>> = MutableLiveData()
    var overTimeResponse: MutableLiveData<BaseResource<Void>> = MutableLiveData()


    fun getLeaveTypes(timeFlag: Boolean) {

        leaveTypesResponse.value = BaseResource.loading(leaveTypesResponse.value?.data)

        dispose(
            dataManager.getLeaveTypes(timeFlag),
            ::onGetLeaveTypesSuccess,
            { e ->
                //error handling
                e.message?.let {
                    leaveTypesResponse.postValue(BaseResource.error(it, null))
                }


            })

    }

    fun sendLeaveRequest(
        startDate: String,
        endDate: String,
        leaveName: String,
        leaveDescription: String,
        leaveTypeId: Int
    ) {
        navigator.onSendingRequest()
        dispose(
            dataManager.sendLeaveRequest(
                startDate,
                endDate,
                leaveName,
                leaveDescription,
                leaveTypeId
            ),
            ::onSendLeaveRequestSuccess,
            { e ->
                //error handling
                e.message?.let {
                    sendLeaveRequestResponse.postValue(BaseResource.error(it, null))
                    navigator.onSendingRequestError("an Error accord")
                }
            })
    }

    fun requestOvertime(
        startTime: String,
        endTime: String
    ) {

        overTimeResponse.value = BaseResource.loading(overTimeResponse.value?.data)
        navigator.onSendingRequest()

        dispose(
            dataManager.requestOvertime(
                startTime,
                endTime
            ),
            ::onOverTimeSuccess,
            { e ->
                //error handling
                e.message?.let {
                    overTimeResponse.postValue(BaseResource.error(it, null))
                    navigator.onSendingRequestError("an Error accord")
                }
            })
    }


    fun sendFingerPrintRequest(description: String, type: Int, time: String) {


        missPunchResponse.value = BaseResource.loading(missPunchResponse.value?.data)
        navigator.onSendingRequest()

        dispose(
            dataManager.sendFingerPrintRequest(description, type, time),
            ::onMissPunchSuccess,
            { e ->
                //error handling
                e.message?.let {
                    missPunchResponse.postValue(BaseResource.error(it, null))
                    navigator.onSendingRequestError("an Error accord")
                }
            })
    }

    private fun onMissPunchSuccess(result: BaseResource<Void>) {

        missPunchResponse.postValue(result)

        result.message?.let {
            navigator.onSendingRequestError(result.message)
        }

        navigator.onSendingRequestSuccess()

    }

    private fun onOverTimeSuccess(result: BaseResource<Void>) {

        overTimeResponse.postValue(result)

        if (result.message!=null){
            navigator.onSendingRequestError(result.message)
        }else{
            navigator.onSendingRequestSuccess()
        }
    }

    private fun onGetLeaveTypesSuccess(result: BaseResource<RequestTypesResponse>) {

        leaveTypesResponse.postValue(result)

        result.message?.let {
            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)
        }
        result.data?.let {
        }

    }

    private fun onSendLeaveRequestSuccess(result: BaseResource<SendRequestResponse>) {

        sendLeaveRequestResponse.postValue(result)

        result.message?.let {
            navigator.onSendingRequestError(result.message)
        }

        result.data?.let {
            navigator.onSendingRequestSuccess()

        }

    }


}