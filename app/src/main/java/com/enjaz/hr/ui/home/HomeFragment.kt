package com.enjaz.hr.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetHomeBinding
import com.enjaz.hr.ui.attendance.ICalenderListener
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FramgnetHomeBinding, IHomeInteractionListener, HomeViewModel>(),
    IHomeInteractionListener, ICalenderListener {

    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var usersAdapter: UsersAdapter
    lateinit var calenderAdapter: CalenderAdapter


    override fun getLayoutId(): Int {
        return R.layout.framgnet_home

    }

    override fun getViewModel(): HomeViewModel {
        return homeViewModel
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getNavigator(): IHomeInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getViewModel().getdata()

        getViewDataBinding().rv.apply {
            adapter = usersAdapter
        }

        getViewDataBinding().rvDate.apply {
            adapter = calenderAdapter
        }



    }


    override fun showAllClasses() {
        // navigate to classes view ==> findNavController().navigate(R.id.classesFragment)
    }

    override fun showAllTasks() {
        // navigate to tasks view ==> findNavController().navigate(R.id.classesFragment)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("abdalla19988", "abdalla19988")

        usersAdapter = UsersAdapter(requireContext(), mutableListOf())
        calenderAdapter = CalenderAdapter(requireContext(), mutableListOf())
        calenderAdapter.setOnItemClickListener(this)

    }
    override fun onMyItemClick(position: Int) {
        var oldPosition = 0
        for (x in 0 until homeViewModel.dates.value!!.size) {
            if (homeViewModel.dates.value!![x].is_selected) {
                oldPosition = x
            }
            homeViewModel.dates.value!![x].is_selected = false
        }
        homeViewModel.dates.value!![position].is_selected = true
        calenderAdapter.notifyItemChanged(position)
        calenderAdapter.notifyItemChanged(oldPosition)

    }


}

interface IHomeInteractionListener : BaseNavigator {
    fun showAllClasses()
    fun showAllTasks()
}
