package com.enjaz.hr.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.data.model.Data
import com.enjaz.hr.data.model.Notification
import com.enjaz.hr.databinding.ItemAttendanceBinding
import com.enjaz.hr.ui.base.BaseDataItemInteractionListener
import com.enjaz.hr.ui.base.BaseDataItemsAdapter
import java.io.File

open class NotificationsAdapter(
    protected var context: Context, objects: List<Notification>
) : BaseDataItemsAdapter<Notification>(objects, null) {

    protected lateinit var binding: ItemAttendanceBinding

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_notification,
            parent,
            false
        )
    }


}

interface INotificationItemActionListener : BaseDataItemInteractionListener {
    fun onNotificationClick(string: Data)
}
