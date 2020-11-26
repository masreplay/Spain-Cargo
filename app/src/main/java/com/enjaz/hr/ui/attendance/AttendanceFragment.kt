package com.enjaz.hr.ui.attendance

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentAttendanceBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.util.snackBar

import dagger.hilt.android.AndroidEntryPoint


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

        getViewModel().getAttendanceData(11,2020)


        getViewDataBinding().rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = attendanceAdapter
        }

    }



    override fun onRequestClick() {
        findNavController().navigate(R.id.requestTypeFragment)
    }

    override fun showSnack(string: String, color: String, drawable: Int?) {
            snackBar(string, drawable, color, getViewDataBinding().parent, requireContext())

    }
}


interface IAttendanceInteractionListener : BaseNavigator {
    fun showSnack(string: String, color: String, drawable: Int?)

}
