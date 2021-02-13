package com.enjaz.hr.ui.employees

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.data.model.home.Employee
import com.enjaz.hr.data.model.home.Teammate
import com.enjaz.hr.databinding.ItemEmployeeBinding
import com.enjaz.hr.ui.base.BaseDataItemInteractionListener
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class EmployeesAdapter(
    protected var context: Context, objects: MutableList<Employee>
) : BaseDataItemsAdapter<Employee>(objects, null) {

    protected lateinit var binding: ItemEmployeeBinding

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_employee,
            parent,
            false
        )
    }


}

interface IEmployeeItemActionListener : BaseDataItemInteractionListener {
    fun onEmployeeClick(employee: Employee)
}
