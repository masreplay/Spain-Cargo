package com.enjaz.hr.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.enjaz.hr.ui.onboarding.fragments.OnboardingFirstFragment
import com.enjaz.hr.ui.onboarding.fragments.OnboardingFourthFragment
import com.enjaz.hr.ui.onboarding.fragments.OnboardingSecFragment
import com.enjaz.hr.ui.onboarding.fragments.OnboardingThirdFragment
import com.enjaz.hr.ui.profile.ProfileFragment


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> OnboardingFirstFragment()
            1 -> OnboardingSecFragment()
            2 -> OnboardingThirdFragment()
            else -> OnboardingFourthFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Info"
            1 -> "Info"
            2 -> "Info"
            else -> "Info"

        }
    }
}