package com.enjaz.hr.ui.onboarding

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager
import com.enjaz.hr.R
import com.enjaz.hr.databinding.ActivityOnboardingBinding
import com.enjaz.hr.ui.base.BaseActivity
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity :
    BaseActivity<ActivityOnboardingBinding, IOnboardingInteractionListener, OnboardingViewModel>(),
    IOnboardingInteractionListener {
    private val onboardingViewModel: OnboardingViewModel by viewModels()

    private val MAX_STEP = 3

    override fun getLayoutId(): Int {
        return R.layout.activity_onboarding
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewDataBinding().pager.adapter = ViewPagerAdapter(supportFragmentManager)

        getViewDataBinding().pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

                bottomProgressDots(position)

                if (position >= 2) {
                    getViewDataBinding().btnNext.text = "Done"
                    getViewDataBinding().btnNext.setBackgroundColor(Color.parseColor("#F4A937"))
                    getViewDataBinding().btnNext.setTextColor(Color.WHITE)
                } else {
                    getViewDataBinding().btnNext.text = "Next"
                    getViewDataBinding().btnNext.setBackgroundColor(resources.getColor(R.color.white))
                    getViewDataBinding().btnNext.setTextColor(resources.getColor(R.color.carbon_grey_800))
                }


            }
        })




        getViewDataBinding().btnNext.setOnClickListener(View.OnClickListener {
            if (getViewDataBinding().pager.currentItem < 2) {
                getViewDataBinding().pager.currentItem = getViewDataBinding().pager.currentItem + 1
            }
        })
    }

    override fun getNavigator(): IOnboardingInteractionListener {
        return this
    }


    override fun getViewModel(): OnboardingViewModel {
        return onboardingViewModel
    }

    private fun bottomProgressDots(current_index: Int) {
        val dotsLayout = findViewById<LinearLayout>(R.id.layoutDots)
        val dots = arrayOfNulls<ImageView>(MAX_STEP)
        dotsLayout.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val width_height = 15
            val params =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams(
                        width_height,
                        width_height
                    )
                )
            params.setMargins(10, 10, 10, 10)
            dots[i]!!.layoutParams = params
            dots[i]!!.setImageResource(R.drawable.shape_circle)
            dots[i]!!
                .setColorFilter(resources.getColor(R.color.grey_200), PorterDuff.Mode.SRC_IN)
            dotsLayout.addView(dots[i])
        }
        if (dots.size > 0) {
            dots[current_index]!!.setImageResource(R.drawable.shape_circle)
            dots[current_index]
                ?.setColorFilter(resources.getColor(R.color.orange), PorterDuff.Mode.SRC_IN)
        }
    }


}

interface IOnboardingInteractionListener : BaseNavigator {

}
