package com.enjaz.university.ui.schedual

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.enjaz.university.R
import com.enjaz.university.databinding.FragmentScheduleBinding
import com.enjaz.university.ui.base.BaseFragment
import com.enjaz.university.ui.base.BaseNavigator
import com.enjaz.university.util.calendar.HorizontalCalendar
import com.enjaz.university.util.calendar.utils.HorizontalCalendarListener
import java.util.*


class ScheduleFragment : BaseFragment<FragmentScheduleBinding, IScheduleInteractionListener, ScheduleViewModel>(),IScheduleInteractionListener {


    lateinit var scheduleAdapter: ScheduleAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_schedule
    }

    override fun getViewModelClass(): Class<ScheduleViewModel> {
    return ScheduleViewModel::class.java
    }

    override fun getNavigator(): IScheduleInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleAdapter = ScheduleAdapter(activity!!, mutableListOf())

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()

        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, 0)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)

        val horizontalCalendar: HorizontalCalendar =
            HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .configure()
                .textSize(12.0F, 16.0F, 12.0F)
                .formatTopText("E")
                .formatMiddleText("d")
                .showBottomText(false)
                .end()
                .defaultSelectedDate(startDate)
                .build()


        getViewModel().getSchedulle((horizontalCalendar.selectedDate.time.month + 1).toString() + " " + (horizontalCalendar.selectedDate.time.date + 4).toString())


        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar?, position: Int) { //do something


                getViewModel().getSchedulle((horizontalCalendar.selectedDate.time.month + 1).toString() + " " + horizontalCalendar.selectedDate.time.date.toString())


            }
        }


        getViewDataBinding().recycler.adapter = scheduleAdapter
        getViewDataBinding().recycler.layoutManager = LinearLayoutManager(activity)


    }


}
interface IScheduleInteractionListener : BaseNavigator {


}
