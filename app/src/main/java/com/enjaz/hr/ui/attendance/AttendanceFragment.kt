package com.enjaz.hr.ui.attendance

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.vvalidator.util.show
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentAttendanceBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.util.calendar.HorizontalCalendar
import com.enjaz.hr.util.calendar.utils.HorizontalCalendarListener
import com.enjaz.hr.util.makeGone
import com.enjaz.hr.util.snackBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AttendanceFragment :
    BaseFragment<FragmentAttendanceBinding, IAttendanceInteractionListener, AttendanceViewModel>(),
    IAttendanceInteractionListener, IAttendanceItemActionListener {

    private val attendanceViewModel: AttendanceViewModel by viewModels()

    private lateinit var attendanceAdapter: AttendanceAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_attendance
    }

    override fun getViewModel(): AttendanceViewModel {
        return attendanceViewModel
    }


    override fun getNavigator(): IAttendanceInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attendanceAdapter = AttendanceAdapter(requireContext(), mutableListOf())
        attendanceAdapter.setOnItemClickListener(this)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



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

        getViewModel().getAttendanceData((calendar.get(Calendar.MONTH)+1),calendar.get(Calendar.YEAR))


        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar?, position: Int) { //do something

                getViewModel().getAttendanceData((horizontalCalendar.selectedDate.time.month + 1) , horizontalCalendar.selectedDate.get(Calendar.YEAR))



            }
        }


        getViewDataBinding().rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = attendanceAdapter
        }

    }



    override fun onRequestClick() {
        findNavController().navigate(R.id.requestMainTypesFragment)
    }

    override fun showSnack(string: String, color: String, drawable: Int?) {
            snackBar(string, drawable, color, getViewDataBinding().parent, requireContext())

    }

    override fun noAttendance() {
        getViewDataBinding().lytNoData.show()
    }

    override fun attendanceAvailable() {
        getViewDataBinding().lytNoData.makeGone()
    }
}


interface IAttendanceInteractionListener : BaseNavigator {
    fun showSnack(string: String, color: String, drawable: Int?)
    fun noAttendance()
    fun attendanceAvailable()
}
