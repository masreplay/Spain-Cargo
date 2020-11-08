package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.framgnet_requests.*

@AndroidEntryPoint
class RequestsFragment :
    BaseFragment<FramgnetRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()
    lateinit var fragmentSent: Fragment
    lateinit var fragmentReceived: Fragment
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

        getViewModel().getdata()


        fragmentSent = SentRequestsFragment()
        fragmentReceived = ReceivedRequestsFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentReceived).commit()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container, fragmentSent).commit()
                } else {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container, fragmentReceived).commit()
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)
    }
}

interface IRequestsInteractionListener : BaseNavigator {

}
