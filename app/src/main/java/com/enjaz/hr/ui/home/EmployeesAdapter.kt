package com.enjaz.hr.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.data.model.home.Teammate
import com.enjaz.hr.databinding.ItemDashboardBinding
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class EmployeesAdapter(
    protected var context: Context, objects: MutableList<Teammate>
) : BaseDataItemsAdapter<Teammate>(objects, null) {

    protected lateinit var binding: ItemDashboardBinding

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
