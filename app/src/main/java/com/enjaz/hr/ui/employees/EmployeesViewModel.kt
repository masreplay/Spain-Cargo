package com.enjaz.hr.ui.employees

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.home.Teammate
import com.enjaz.hr.ui.base.BaseViewModel

class EmployeesViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IEmployeesInteractionListener>(
    dataManager
) {

    var employeesResponse: MutableLiveData<BaseResource<List<Teammate>>> = MutableLiveData()


    fun getMyEmployees() {

        employeesResponse.value = BaseResource.loading(employeesResponse.value?.data)

        dispose(
            dataManager.getMyTeamMates(),
            ::onGetTeamSuccess,
            { e ->
                //error handling
                e.message?.let {
                    employeesResponse.postValue(BaseResource.error(it, null))
                }


            })
        refreshListener.postValue(View.OnClickListener { getMyEmployees() })


    }

    private fun onGetTeamSuccess(result: BaseResource<List<Teammate>>) {
        result.data?.let {
            employeesResponse.postValue(result)
        }
    }
}