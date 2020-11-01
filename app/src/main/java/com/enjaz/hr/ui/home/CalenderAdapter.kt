package com.enjaz.hr.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.data.model.DateItem
import com.enjaz.hr.databinding.ItemDateBinding
import com.enjaz.hr.databinding.ItemDateHomeBinding
import com.enjaz.hr.ui.base.BaseDataItemInteractionListener
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class CalenderAdapter(
    protected var context: Context, objects: MutableList<String>
) : BaseDataItemsAdapter<String>(objects, null) {

    protected lateinit var binding: ItemDateHomeBinding

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_date_home,
            parent,
            false
        )
    }


}

interface ICalenderListener : BaseDataItemInteractionListener {
    fun onMyItemClick(position: Int)
}

