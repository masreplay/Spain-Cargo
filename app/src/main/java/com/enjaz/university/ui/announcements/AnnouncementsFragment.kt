package com.enjaz.university.ui.announcements

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.enjaz.university.R
import com.enjaz.university.databinding.FragmentAnnouncementsBinding
import com.enjaz.university.ui.base.BaseFragment
import com.enjaz.university.ui.base.BaseNavigator
import com.enjaz.university.util.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class EventsFragment :
    BaseFragment<FragmentAnnouncementsBinding, IEventsInteractionListener, AnnouncementsViewModel>(),
    IEventsInteractionListener {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = getViewDataBinding().tabLayout
        viewPager = getViewDataBinding().viewPager

        tabLayout!!.addTab(tabLayout!!.newTab().setText("All Items"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Exams"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Classes"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("General"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = ViewPagerAdapter(context!!, parentFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager!!.currentItem = tab!!.position

            }


        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_announcements

    }

    override fun getViewModelClass(): Class<AnnouncementsViewModel> {
        return AnnouncementsViewModel::class.java
    }

    override fun getNavigator(): IEventsInteractionListener {
        return this
    }

}


interface IEventsInteractionListener : BaseNavigator {


}