package com.enjaz.hr.ui.onboarding.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentThirdOnboardingBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.onboarding.IOnboardingInteractionListener
import com.enjaz.hr.ui.onboarding.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnboardingThirdFragment :
    BaseFragment<FragmentThirdOnboardingBinding, IOnboardingInteractionListener, OnboardingViewModel>(),
    IOnboardingInteractionListener {

    private val onboardingViewModel: OnboardingViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.fragment_third_onboarding
    }

    override fun getViewModel(): OnboardingViewModel {
        return onboardingViewModel
    }


    override fun getNavigator(): IOnboardingInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}


