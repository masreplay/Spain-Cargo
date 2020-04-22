package com.enjaz.university.ui.finalGrades

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.university.R
import com.enjaz.university.data.model.grades.Grade
import com.enjaz.university.databinding.ItemFinalGradesChildBinding
import com.enjaz.university.ui.base.BaseDataItemInteractionListener
import com.enjaz.university.ui.base.BaseDataItemsAdapter

open class GradesChildAdapter(protected var context: Context, objects: MutableList<Grade>) :
    BaseDataItemsAdapter<Grade>(objects, null) {

    protected lateinit var binding: ItemFinalGradesChildBinding


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_final_grades_child,
            parent,
            false
        )


    }


}

interface IFinalGradeItemActionListener : BaseDataItemInteractionListener {
    fun onFinalGradeItemClick(model: Grade)
}