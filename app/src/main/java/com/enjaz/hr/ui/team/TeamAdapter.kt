package com.enjaz.hr.ui.team

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.databinding.ItemSentBinding
import com.enjaz.hr.databinding.ItemTeamBinding
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class TeamAdapter(
    protected var context: Context, objects: MutableList<String>
) : BaseDataItemsAdapter<String>(objects, null) {

    protected lateinit var binding: ItemTeamBinding

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_team,
            parent,
            false
        )
    }


}