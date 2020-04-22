package com.enjaz.university.ui.announcements.allEvents

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.enjaz.university.data.AppDataManager
import com.enjaz.university.data.model.BaseResource
import com.enjaz.university.data.model.BaseResponse
import com.enjaz.university.data.model.announcements.AnnouncementResponse
import com.enjaz.university.ui.base.BaseViewModel

class AllEventsViewModel(dataManager: AppDataManager) :
    BaseViewModel<IAllEventsInteractionListener>(
        dataManager
    ) {


    var getAnnouncementResponse: MutableLiveData<BaseResource<BaseResponse<AnnouncementResponse>>> =
        MutableLiveData()


    fun getAnnouncements(filter: String) {
        dispose(dataManager.getAnnouncements(filter), ::onGetAnnouncementsSuccess, { e ->
            run {

                Log.d("ausamah", e.localizedMessage)


            }
        })
    }


    private fun onGetAnnouncementsSuccess(result: BaseResource<BaseResponse<AnnouncementResponse>>) {
        Log.d("ausamah", " success")

        getAnnouncementResponse.value = result

    }


}