package com.enjaz.hr.ui.home

import android.os.Bundle
import android.text.Html
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

        getViewModel().mylog()

        // TODO: 10/7/2020 replace deprecated
        val text =
            "<font color=#1575ff>20-</font><font color=#f6ae3f>5</font><font color=#1575ff>-</font><font color=#f64a3f>3</font>"
        tv_attendance_value.text = Html.fromHtml(text)


        val section1 = DonutSection(
            name = "section_1",
            color = ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
            amount = 25f
        )

        donut_leave.cap = 100f
        donut_leave.submitData(listOf(section1))

        val section2 = DonutSection(
            name = "section_2",
            color = ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
            amount = 65f
        )

        val section3 = DonutSection(
            name = "section_3",
            color = ContextCompat.getColor(requireActivity(), R.color.orange),
            amount = 15f
        )
        val section4 = DonutSection(
            name = "section_4",
            color = ContextCompat.getColor(requireActivity(), R.color.red_100),
            amount = 20f
        )
        donut_attendance.cap = 100f
        donut_attendance.submitData(listOf(section2, section3, section4))
    }


    override fun showAllClasses() {
        // navigate to classes view ==> findNavController().navigate(R.id.classesFragment)
    }

    override fun showAllTasks() {
        // navigate to tasks view ==> findNavController().navigate(R.id.classesFragment)

    }


}

interface IHomeInteractionListener : BaseNavigator {
    fun showAllClasses()
    fun showAllTasks()
}
