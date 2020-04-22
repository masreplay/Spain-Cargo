package com.enjaz.university.util

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.enjaz.university.ui.announcements.allEvents.AllEventsFragment


class ViewPagerAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    internal var totalTabs: Int
) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                val bundle = Bundle()
                val allEventsFragment = AllEventsFragment()
                bundle.putString("filter", "")
                allEventsFragment.setArguments(bundle)
                return allEventsFragment
            }
            1 -> {
                val bundle = Bundle()
                val allEventsFragment = AllEventsFragment()
                bundle.putString("filter", "Exams")
                allEventsFragment.setArguments(bundle)
                return allEventsFragment
            }
            2 -> {
                // val movieFragment = MovieFragment()
                val bundle = Bundle()
                val allEventsFragment = AllEventsFragment()
                bundle.putString("filter", "Classes")
                allEventsFragment.setArguments(bundle)
                return allEventsFragment
            }
            else -> {
                val bundle = Bundle()
                val allEventsFragment = AllEventsFragment()
                bundle.putString("filter", "General")
                allEventsFragment.setArguments(bundle)
                return allEventsFragment
            }
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}