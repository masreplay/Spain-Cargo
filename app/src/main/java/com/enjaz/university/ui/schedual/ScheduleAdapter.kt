package com.enjaz.university.ui.schedual

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.university.R
import com.enjaz.university.data.model.schedule.ScheduleResponse
import com.enjaz.university.databinding.ItemScheduleBinding
import com.enjaz.university.ui.base.BaseDataItemInteractionListener
import com.enjaz.university.ui.base.BaseDataItemsAdapter

open class ScheduleAdapter(protected var context: Context, objects: MutableList<ScheduleResponse>) :
    BaseDataItemsAdapter<ScheduleResponse>(objects, null) {

    protected lateinit var binding: ItemScheduleBinding


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_schedule,
            parent,
            false
        )


    }


}

interface IScheduleItemActionListener : BaseDataItemInteractionListener {
    fun onScheduleItemClick(model: ScheduleResponse)
}