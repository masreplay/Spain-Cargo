package com.enjaz.hr.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import app.futured.donut.DonutSection
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetHomeBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.framgnet_home.*


@AndroidEntryPoint
class HomeFragment : BaseFragment<FramgnetHomeBinding, IHomeInteractionListener, HomeViewModel>(),
    IHomeInteractionListener {

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


        val section1 = DonutSection(
            name = "section_1",
            color = ContextCompat.getColor(requireActivity(), R.color.green_400),
            amount = 25f
        )

        val section2 = DonutSection(
            name = "section_2",
            color = ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
            amount = 45f
        )

        val section3 = DonutSection(
            name = "section_3",
            color = ContextCompat.getColor(requireActivity(), R.color.red_100),
            amount = 10f
        )
        val section4 = DonutSection(
            name = "section_4",

            color = ContextCompat.getColor(requireActivity(), R.color.orange),
            amount = 20f
        )
        donut_attendance.cap = 100f
        donut_attendance.submitData(listOf(section1, section2, section3, section4))


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
    }


}

interface IHomeInteractionListener : BaseNavigator {
    fun showAllClasses()
    fun showAllTasks()
}
