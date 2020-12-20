package com.enjaz.hr.ui.team

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.home.Teammate
import com.enjaz.hr.ui.base.BaseViewModel

class TeamViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<ITeamInteractionListener>(
    dataManager
) {

    var teammembers: MutableLiveData<BaseResource<List<Teammate>>> = MutableLiveData()


    fun getMyTeammates() {


        teammembers.value = BaseResource.loading(teammembers.value?.data)



        dispose(
            dataManager.getMyTeamMates(),
            ::onGetTeamSuccess,
            { e ->
                //error handling
                e.message?.let {
                    teammembers.postValue(BaseResource.error(it, null))
                    Log.d("error", it)
                }


            })
        refreshListener.postValue(View.OnClickListener { getMyTeammates() })


    }

    private fun onGetTeamSuccess(result: BaseResource<List<Teammate>>) {
        result.data?.let {
            teammembers.postValue(result)

        }
    }
}