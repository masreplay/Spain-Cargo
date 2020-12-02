package com.enjaz.hr.ui.requests

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.R
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.getLeaveRequests.LeaveRequestResponse
import com.enjaz.hr.ui.base.BaseViewModel

class RequestsViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IRequestsInteractionListener>(
    dataManager
) {

    var leaveRequestResponse: MutableLiveData<BaseResource<LeaveRequestResponse>> = MutableLiveData()

    var cancelMyRequestResponse: MutableLiveData<BaseResource<String>> = MutableLiveData()

    fun getLeaveRequests(isManager: Boolean,filter:Int?=null){


        leaveRequestResponse.value = BaseResource.loading(leaveRequestResponse.value?.data)



        dispose(
            dataManager.getLeaveRequests(isManager,filter),
            ::onGetLeaveRequestsSuccess,
            { e ->
                //error handling
                e.message?.let { leaveRequestResponse.postValue(BaseResource.error(it, null))
                    Log.d("error",it)
                }


            })
        refreshListener.postValue(View.OnClickListener { getLeaveRequests(isManager,filter) })



    }

    fun cancelMyRequest(workCorrelationId: String,newStatus:Int){


        cancelMyRequestResponse.value = BaseResource.loading(cancelMyRequestResponse.value?.data)



        dispose(
            dataManager.cancelMyRequest(workCorrelationId,newStatus),
            ::onCancelRequestsSuccess,
            { e ->
                //error handling
                e.message?.let { cancelMyRequestResponse.postValue(BaseResource.error(it, null))
                    Log.d("error",it)
                }


            })
        refreshListener.postValue(View.OnClickListener { cancelMyRequest(workCorrelationId,newStatus) })



    }

    private fun onGetLeaveRequestsSuccess(result: BaseResource<LeaveRequestResponse>) {


        if (result.message !=null){


            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)


        }else result.data?.let {

            leaveRequestResponse.postValue(result)

            if (it.isEmpty()){
                navigator.noRequests()
            }else{
                navigator.requestsAvailable()
            }




        }




    }
    private fun onCancelRequestsSuccess(result: BaseResource<String>) {


        if (result.message !=null){


            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)


        }else {

            cancelMyRequestResponse.postValue(result)

            getLeaveRequests(false)


        }



    }

}