package com.enjaz.hr.ui.attendance

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentAttendanceBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator

import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class AttendanceFragment :
    BaseFragment<FragmentAttendanceBinding, IAttendanceInteractionListener, AttendanceViewModel>(),
    IAttendanceInteractionListener, ICalenderListener {

    private val attendanceViewModel: AttendanceViewModel by viewModels()

    lateinit var attendanceAdapter: AttendanceAdapter
    lateinit var calenderAdapter: CalenderAdapter


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
        calenderAdapter = CalenderAdapter(requireContext(), mutableListOf())
        calenderAdapter.setOnItemClickListener(this)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().getdata()

        getViewDataBinding().rvDate.apply {
            adapter = calenderAdapter
        }



        getViewModel().getdata()

        getViewDataBinding().rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = attendanceAdapter
        }

    }

    override fun onMyItemClick(position: Int) {
        var oldPosition = 0
        for (x in 0 until attendanceViewModel.dates.value!!.size) {
            if (attendanceViewModel.dates.value!![x].is_selected) {
                oldPosition = x
            }
            attendanceViewModel.dates.value!![x].is_selected = false
        }
        attendanceViewModel.dates.value!![position].is_selected = true
        calenderAdapter.notifyItemChanged(position)
        calenderAdapter.notifyItemChanged(oldPosition)

    }
}


interface IAttendanceInteractionListener : BaseNavigator {

}
