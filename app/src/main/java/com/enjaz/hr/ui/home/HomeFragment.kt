package com.enjaz.hr.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.futured.donut.DonutSection
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetHomeBinding
import com.enjaz.hr.ui.attendance.ICalenderListener
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.framgnet_home.*


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

        val section1 = DonutSection(
            name = "section_1",
            color = ContextCompat.getColor(requireActivity(), R.color.green),
            amount = 31f
        )

        val section2 = DonutSection(
            name = "section_2",
            color = ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
            amount = 45f
        )

        val section3 = DonutSection(
            name = "section_3",
            color = ContextCompat.getColor(requireActivity(), R.color.orange),
            amount = 17f
        )
        val section4 = DonutSection(
            name = "section_4",
            color = ContextCompat.getColor(requireActivity(), R.color.red),
            amount = 7f
        )
        donut_attendance.cap = 100f
        donut_attendance.submitData(listOf(section1,section2, section3, section4))
    }


    override fun showTeam() {
        findNavController().navigate(R.id.teamFragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    fun showTeam()
}
