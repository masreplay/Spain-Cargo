package com.enjaz.hr.ui.attendance

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentAttendanceBinding
import com.enjaz.hr.databinding.FramgnetHomeBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttendanceFragment : BaseFragment<FragmentAttendanceBinding, IAttendanceInteractionListener, AttendanceViewModel>(),
    IAttendanceInteractionListener {

    private val attendanceViewModel: AttendanceViewModel by viewModels()

    lateinit var attendanceAdapter: AttendanceAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_attendance
    }

    override fun getViewModel(): AttendanceViewModel {
        return attendanceViewModel
    }

    override fun getViewModelClass(): Class<AttendanceViewModel> {
        return AttendanceViewModel::class.java
    }

    override fun getNavigator(): IAttendanceInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attendanceAdapter= AttendanceAdapter(requireContext(), mutableListOf())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().getdata()

        getViewDataBinding().rv.apply {
            layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
            adapter=attendanceAdapter
        }
    }








}

interface IAttendanceInteractionListener : BaseNavigator {

}
