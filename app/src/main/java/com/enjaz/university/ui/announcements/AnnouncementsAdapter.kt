package com.enjaz.university.ui.announcements

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.university.R
import com.enjaz.university.data.model.announcements.AnnouncementResponse
import com.enjaz.university.databinding.ItemAnnouncementBinding
import com.enjaz.university.ui.base.BaseDataItemInteractionListener
import com.enjaz.university.ui.base.BaseDataItemsAdapter

open class AnnouncementsAdapter(
    protected var context: Context,
    objects: MutableList<AnnouncementResponse>
) : BaseDataItemsAdapter<AnnouncementResponse>(objects, null) {

    protected lateinit var binding: ItemAnnouncementBinding


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_announcement,
            parent,
            false
        )


    }


}

interface IAnnouncementItemActionListener : BaseDataItemInteractionListener {
    fun onAnnouncementItemClick(model: AnnouncementResponse)
}