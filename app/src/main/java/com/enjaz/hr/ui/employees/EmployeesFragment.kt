package com.enjaz.hr.ui.employees

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.enjaz.hr.R
import com.enjaz.hr.data.model.home.Teammate
import com.enjaz.hr.databinding.FramgnetEmployeesBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesFragment :
    BaseFragment<FramgnetEmployeesBinding, IEmployeesInteractionListener, EmployeesViewModel>(),
    IEmployeesInteractionListener, IEmployeeItemActionListener {

    private val teamViewModel: EmployeesViewModel by viewModels()

    lateinit var employeesAdapter: EmployeesAdapter


    override fun getLayoutId(): Int {
        return R.layout.framgnet_employees
    }

    override fun getViewModel(): EmployeesViewModel {
        return teamViewModel
    }


    override fun getNavigator(): IEmployeesInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().getMyEmployees()
        getViewDataBinding().rv.apply {
            adapter = employeesAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        employeesAdapter = EmployeesAdapter(requireContext(), mutableListOf())
        employeesAdapter.setOnItemClickListener(this)

    }

    override fun onEmployeeClick(teammate: Teammate) {
        val action: NavDirections =
            EmployeesFragmentDirections.actionEmployeeFragmentToAttendanceFragment2(
                employee = teammate
            )
        findNavController().navigate(action)
    }


}

interface IEmployeesInteractionListener : BaseNavigator {

}




