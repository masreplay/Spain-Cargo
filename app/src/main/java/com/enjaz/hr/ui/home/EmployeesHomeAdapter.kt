package com.enjaz.hr.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.data.model.Types
import com.enjaz.hr.data.model.home.Employee
import com.enjaz.hr.data.model.home.Teammate
import com.enjaz.hr.databinding.ItemEmployeeHomeBinding
import com.enjaz.hr.ui.base.BaseDataItemInteractionListener
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class EmployeesHomeAdapter(
    protected var context: Context, objects: MutableList<Employee>
) : BaseDataItemsAdapter<Employee>(objects, null) {

    protected lateinit var binding: ItemEmployeeHomeBinding

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_employee_home,
            parent,
            false
        )
    }


}

interface IEmployeeItemActionListener : BaseDataItemInteractionListener {
    fun onItemClick(employee: Employee)
}
