package com.enjaz.hr.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.databinding.ItemSalaryDetailsBinding
import com.enjaz.hr.ui.base.BaseDataItemInteractionListener
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class SalaryDetailsAdapter(protected var context: Context, objects: MutableList<String>) :
    BaseDataItemsAdapter<String>(objects, null) {

    protected lateinit var binding: ItemSalaryDetailsBinding


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_salary_details,
            parent,
            false
        )


    }


}

interface ISalartDetailsItemActionListener : BaseDataItemInteractionListener {
    fun onItemClick(model: String)
}