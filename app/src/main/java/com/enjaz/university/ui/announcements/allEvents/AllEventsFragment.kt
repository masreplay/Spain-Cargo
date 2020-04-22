package com.enjaz.university.ui.announcements.allEvents

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.enjaz.university.R
import com.enjaz.university.databinding.FragmentAllEventsBinding
import com.enjaz.university.ui.announcements.AnnouncementsAdapter
import com.enjaz.university.ui.base.BaseFragment
import com.enjaz.university.ui.base.BaseNavigator


class AllEventsFragment :
    BaseFragment<FragmentAllEventsBinding, IAllEventsInteractionListener, AllEventsViewModel>(),
    IAllEventsInteractionListener {

    lateinit var filter: String

    lateinit var announcementsAdapter: AnnouncementsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            filter = bundle.getString("filter", "")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announcementsAdapter =
            AnnouncementsAdapter(
                activity!!,
                mutableListOf()
            )


        getViewDataBinding().recycler.adapter = announcementsAdapter
        getViewDataBinding().recycler.layoutManager = LinearLayoutManager(activity)

        getViewModel().getAnnouncements(filter)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_all_events


    }

    override fun getViewModelClass(): Class<AllEventsViewModel> {
        return AllEventsViewModel::class.java
    }

    override fun getNavigator(): IAllEventsInteractionListener {

        return this
    }

}

interface IAllEventsInteractionListener : BaseNavigator {


}