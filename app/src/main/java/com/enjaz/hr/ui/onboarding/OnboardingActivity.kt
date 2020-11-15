package com.enjaz.hr.ui.onboarding

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.enjaz.hr.MainActivity
import com.enjaz.hr.R
import com.enjaz.hr.databinding.ActivityOnboardingBinding
import com.enjaz.hr.ui.base.BaseActivity
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.ui.login.LoginActivity
import com.enjaz.hr.util.PrefsManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnboardingActivity :
    BaseActivity<ActivityOnboardingBinding, IOnboardingInteractionListener, OnboardingViewModel>(),
    IOnboardingInteractionListener {
    private val onboardingViewModel: OnboardingViewModel by viewModels()

    private val MAX_STEP = 4

    override fun getLayoutId(): Int {
        return R.layout.activity_onboarding
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (PrefsManager.instance!!.getString("boarding")=="1") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else {
            PrefsManager.instance!!.setString("boarding", "1")

        }

        val window: Window = this.getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white))

        getViewDataBinding().pager.adapter = ViewPagerAdapter(supportFragmentManager)

        bottomProgressDots(0)
        getViewDataBinding().btnPrev.hide()







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
                if (position > 0) {
                    getViewDataBinding().btnPrev.show()
                } else getViewDataBinding().btnPrev.hide()
                if (position >= 3) {
                    getViewDataBinding().btnDone.show()
                } else {
                    getViewDataBinding().btnDone.hide()
                }

            }
        })


        getViewDataBinding().btnNext.setOnClickListener(View.OnClickListener {
            if (getViewDataBinding().pager.currentItem < 3) {
                getViewDataBinding().pager.currentItem = getViewDataBinding().pager.currentItem + 1
            }
        })

        getViewDataBinding().btnPrev.setOnClickListener(View.OnClickListener {
            if (getViewDataBinding().pager.currentItem > 0) {
                getViewDataBinding().pager.currentItem = getViewDataBinding().pager.currentItem - 1
            }
        })
        getViewDataBinding().btnDone.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        })

        getViewDataBinding().btClose.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
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
                .setColorFilter(resources.getColor(R.color.blue_light), PorterDuff.Mode.SRC_IN)
            dotsLayout.addView(dots[i])
        }
        if (dots.size > 0) {
            dots[current_index]!!.setImageResource(R.drawable.shape_circle)
            dots[current_index]
                ?.setColorFilter(resources.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
        }
    }


}

interface IOnboardingInteractionListener : BaseNavigator {

}
