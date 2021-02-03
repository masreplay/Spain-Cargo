package com.enjaz.hr.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.hr.BR

abstract class BaseDataItemsAdapter<T>(
    private var items: List<T>,
    private var itemInteractionListener: BaseDataItemInteractionListener?
) : RecyclerView.Adapter<BaseDataItemsAdapter.BaseViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    private fun getLayoutInflater(view: View): LayoutInflater {
        return layoutInflater ?: LayoutInflater.from(view.context).also {
            layoutInflater = it
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            createViewDataBinding(getLayoutInflater(parent), parent, viewType)
        )
    }

    abstract fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.viewBinding.setVariable(BR.item, items[position])
            holder.viewBinding.setVariable(BR.clickListener, itemInteractionListener)
            holder.viewBinding.setVariable(BR.position, position)

        }
    }


    //This function checks if the items aren't updated
    open fun setItems(newItems: List<T>) {
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                if (oldItemPosition < 0 || newItemPosition < 0) {
                    return oldItemPosition == newItemPosition
                }
                val oldPosition = oldItemPosition
                val newPosition = newItemPosition
                return areItemsTheSame(items[oldPosition], newItems[newPosition])
            }

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = newItems.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                if (oldItemPosition < 0 || newItemPosition < 0) {
                    return oldItemPosition == newItemPosition
                }
                val oldPosition = oldItemPosition
                val newPosition = newItemPosition
                return areContentsTheSame(items[oldPosition], newItems[newPosition])
            }

        }).let {
            items = newItems
            it.dispatchUpdatesTo(this)
        }
    }

    open fun setItemsList(newItems: MutableList<T>) {
        items=newItems
        notifyDataSetChanged()
    }

    open fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) ?: false
    }

    open fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) ?: false
    }

    fun setOnItemClickListener(onInteraction: BaseDataItemInteractionListener) {
        this.itemInteractionListener = onInteraction
    }

    class ViewHolder(val viewBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewBinding.root)

    class ItemViewHolder(val viewBinding: ViewDataBinding) : BaseViewHolder(viewBinding.root)

    open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

interface BaseDataItemInteractionListener
