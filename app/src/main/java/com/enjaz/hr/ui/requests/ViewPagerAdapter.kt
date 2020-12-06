package com.enjaz.hr.ui.requests

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> ReceivedRequestsFragment()
            1 -> SentRequestsFragment()
            else -> ReceivedRequestsFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Received"
            1 -> "Sent"
            else -> "Sent"

        }
    }
}