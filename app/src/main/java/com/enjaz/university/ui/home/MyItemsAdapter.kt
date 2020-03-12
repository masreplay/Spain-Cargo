package com.enjaz.university.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.university.R
import com.enjaz.university.data.model.MyItem
import com.enjaz.university.databinding.ItemMineBinding
import com.enjaz.university.ui.base.BaseDataItemInteractionListener
import com.enjaz.university.ui.base.BaseDataItemsAdapter



open class MyItemsAdapter(
	protected  var context: Context, mNavController: NavController?, objects: MutableList<MyItem>) : BaseDataItemsAdapter<MyItem>(objects, null) {

	protected lateinit var binding: ItemMineBinding

	override fun createViewDataBinding(
		inflater: LayoutInflater,
		parent: ViewGroup,
		viewType: Int
	): ViewDataBinding {
		return DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_mine, parent, false)
	}


}
interface IMyItemActionListener : BaseDataItemInteractionListener {
	fun onMyItemClick(myItem: MyItem)
}
