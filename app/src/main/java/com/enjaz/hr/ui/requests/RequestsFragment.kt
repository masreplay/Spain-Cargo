package com.enjaz.hr.ui.requests

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.ui.base.BaseSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.framgnet_requests.*


@AndroidEntryPoint
class RequestsFragment :
    BaseSheetFragment<FramgnetRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()
    override fun getLayoutId(): Int {
        return R.layout.framgnet_requests
    }

    override fun getViewModel(): RequestsViewModel {
        return requestsViewModel
    }

    override fun getNavigator(): IRequestsInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        getViewDataBinding().pager.adapter = ViewPagerAdapter(childFragmentManager)
        getViewDataBinding().pager.currentItem = 1




        tv_received.setOnClickListener {
            tv_received.setTextColor(Color.BLACK)
            tv_sent.setTextColor(Color.WHITE)
            tv_sent.background =
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_round_outline_ripple_16dp
                )
            tv_received.background =
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_round_ripple_16dp
                )

            getViewDataBinding().pager.currentItem = 0
        }

        tv_sent.setOnClickListener {
            tv_sent.setTextColor(Color.BLACK)
            tv_received.setTextColor(Color.WHITE)
            tv_sent.background =
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_round_ripple_16dp
                )
            tv_received.background =
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_round_outline_ripple_16dp
                )

            getViewDataBinding().pager.currentItem = 1
        }

        getViewDataBinding().pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    tv_sent.setTextColor(Color.BLACK)
                    tv_received.setTextColor(Color.WHITE)
                    tv_sent.background =
                        ContextCompat.getDrawable(
                            requireActivity(),
                            R.drawable.bg_round_ripple_16dp
                        )
                    tv_received.background =
                        ContextCompat.getDrawable(
                            requireActivity(),
                            R.drawable.bg_round_outline_ripple_16dp
                        )
                } else {
                    tv_received.setTextColor(Color.BLACK)
                    tv_sent.setTextColor(Color.WHITE)
                    tv_sent.background =
                        ContextCompat.getDrawable(
                            requireActivity(),
                            R.drawable.bg_round_outline_ripple_16dp
                        )
                    tv_received.background =
                        ContextCompat.getDrawable(
                            requireActivity(),
                            R.drawable.bg_round_ripple_16dp
                        )
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.filter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)
    }


    override fun noRequests() {
        TODO("Not yet implemented")
    }

    override fun requestsAvailable() {
        TODO("Not yet implemented")
    }


    override fun showSnack(string: String, color: String, drawable: Int?) {
        TODO("Not yet implemented")
    }

    override fun onFabClick() {
        findNavController().navigate(R.id.requestMainTypesFragment)
    }
}

interface IRequestsInteractionListener : BaseNavigator {

    fun noRequests()
    fun requestsAvailable()
    fun showSnack(string: String, color: String, drawable: Int?)
    fun onFabClick()

}
