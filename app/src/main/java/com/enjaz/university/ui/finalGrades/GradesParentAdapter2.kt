package com.enjaz.university.ui.finalGrades

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.university.R
import com.enjaz.university.data.model.grades.Grade
import com.enjaz.university.data.model.grades.Period
import com.enjaz.university.databinding.ItemFinalGradesParentBinding
import com.enjaz.university.ui.base.BaseDataItemInteractionListener
import com.enjaz.university.ui.base.BaseDataItemsAdapter
import com.enjaz.university.util.ViewAnimation

open class GradesParentAdapter2(
    protected var context: Context,
    val objects: MutableList<MutableList<Period>>
) : BaseDataItemsAdapter<Period>(objects as MutableList<Period>, null) {

    protected lateinit var binding: ItemFinalGradesParentBinding


    val viewAnimation: ViewAnimation = ViewAnimation()
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_final_grades_parent,
            parent,
            false
        )


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return super.onCreateViewHolder(parent, viewType)

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val childLayoutManager =
            LinearLayoutManager(binding.rvChild.context, RecyclerView.VERTICAL, false)
        childLayoutManager.initialPrefetchItemCount = 4
        binding.rvChild.apply {
            layoutManager = childLayoutManager
            adapter = GradesChildAdapter(context, objects = objects[position][position].grades)
            setRecycledViewPool(viewPool)

            binding.btnExpand.setOnClickListener(View.OnClickListener { v ->
                val show =
                    toggleLayoutExpand(
                        !objects[position][position].expanded,
                        v,
                        binding.lytExpand,
                        binding.lytParent
                    )
                objects.get(position).get(position).expanded = show
            })

        }
        if (objects.get(position)[position].expanded) {
            binding.lytExpand.setVisibility(View.VISIBLE)
        } else {
            binding.lytExpand.setVisibility(View.GONE)
        }
        viewAnimation.toggleArrow(
            objects.get(position)[position].expanded,
            binding.btnExpand,
            false
        )

    }

    open fun toggleLayoutExpand(
        show: Boolean,
        view: View,
        lyt_expand: View,
        lyt_parent: View
    ): Boolean {
        viewAnimation.toggleArrow(show, view)
        if (show) {
            viewAnimation.expand(lyt_expand)
        } else {
            viewAnimation.collapse(lyt_expand)
        }
        return show
    }


}

interface IGradeParentItemActionListener : BaseDataItemInteractionListener {
    fun onGradeItemClick(model: Grade)
}