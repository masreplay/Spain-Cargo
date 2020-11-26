package com.enjaz.hr.ui.home

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.futured.donut.DonutSection
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetHomeBinding
import com.enjaz.hr.ui.attendance.ICalenderListener
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.util.calendar.HorizontalCalendar
import com.enjaz.hr.util.calendar.utils.HorizontalCalendarListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.framgnet_home.*
import java.util.*


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
        getViewDataBinding().status.constraintLoading.setBackgroundColor(Color.WHITE)
        getViewDataBinding().status.constraintError.setBackgroundColor(Color.WHITE)


        val calendar = Calendar.getInstance()

        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -12)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 12)

        val horizontalCalendar: HorizontalCalendar =
            HorizontalCalendar.Builder(view, R.id.rv_date)
                .range(startDate, endDate)
                .mode(HorizontalCalendar.Mode.MONTHS)
                .datesNumberOnScreen(5)
                .configure()
                .textSize(12.0F, 16.0F, 12.0F)
                .formatMiddleText("yyyy")
                .formatTopText("LLL")
                .showTopText(true)
                .showBottomText(false)
                .end()
                .defaultSelectedDate(calendar)
                .build()

        getViewModel().getHomeData((calendar.get(Calendar.MONTH)+1),calendar.get(Calendar.YEAR))

//        getViewModel().getSchedulle(
//            (calendar.get(Calendar.MONTH) + 1).toString() + " " + (calendar.get(
//                Calendar.DAY_OF_MONTH
//            )).toString()
//        )




        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar?, position: Int) { //do something

                Handler().postDelayed({
                    getViewModel().getHomeData((horizontalCalendar.selectedDate.time.month + 1) , horizontalCalendar.selectedDate.get(Calendar.YEAR))

                }, 400)


            }
        }

        getViewDataBinding().rv.apply {
            adapter = usersAdapter
        }


        getViewModel().homeResponse.observe(requireActivity(), Observer { response ->
            response.data?.let {



            val section1 = DonutSection(
                name = "section_1",
                color = ContextCompat.getColor(requireActivity(), R.color.green),
                amount = it.vacation.toFloat()
            )


            val section2 = DonutSection(
                    name = "section_2",
                    color = ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
                    amount = it.present.toFloat()
                )

            val section3 =
                DonutSection(
                    name = "section_3",
                    color = ContextCompat.getColor(requireActivity(), R.color.orange),
                    amount = it.delay.toFloat()
                )

            val section4 =
                DonutSection(
                    name = "section_4",
                    color = ContextCompat.getColor(requireActivity(), R.color.red),
                    amount = it.deduction.toFloat()
                )

            donut_attendance.cap = 100f
            donut_attendance.submitData(listOf(section1,section2, section3, section4) )
        }

        })

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
//        var oldPosition = 0
//        for (x in 0 until homeViewModel.dates.value!!.size) {
//            if (homeViewModel.dates.value!![x].is_selected) {
//                oldPosition = x
//            }
//            homeViewModel.dates.value!![x].is_selected = false
//        }
//        homeViewModel.dates.value!![position].is_selected = true
//        calenderAdapter.notifyItemChanged(position)
//        calenderAdapter.notifyItemChanged(oldPosition)

    }


}

interface IHomeInteractionListener : BaseNavigator {
    fun showTeam()
}
