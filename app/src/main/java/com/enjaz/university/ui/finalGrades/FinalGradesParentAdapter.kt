package com.enjaz.university.ui.finalGrades

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.university.R
import com.enjaz.university.data.model.grades.GradesResponse
import com.enjaz.university.util.ViewAnimation
import kotlinx.android.synthetic.main.item_final_grades_parent.view.*

open class FinalGradesParentAdapter(
    var context: Context,
    val parents: MutableList<MutableList<GradesResponse>>
) :
    RecyclerView.Adapter<FinalGradesParentAdapter.ViewHolder>() {


    val viewAnimation: ViewAnimation = ViewAnimation()


    private val viewPool = RecyclerView.RecycledViewPool()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_final_grades_parent, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val p = parents[position]
        holder.periodName.text = p.get(position).periods.get(position).periodName
        holder.periopName2.text = p.get(position).periods.get(position).periodName
        holder.date.text =
            "Date : " + p.get(position).periods.get(position).grades.get(position).examDate.slice(0..10)
        val childLayoutManager =
            LinearLayoutManager(holder.recyclerView.context, RecyclerView.VERTICAL, false)
        childLayoutManager.initialPrefetchItemCount = 4
        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = GradesChildAdapter(context, p.get(position).periods.get(position).grades)
            setRecycledViewPool(viewPool)


            holder.btn_expand.setOnClickListener(View.OnClickListener { v ->
                val show =
                    toggleLayoutExpand(
                        !p.get(position).periods.get(position).expanded,
                        v,
                        holder.lyt_expand,
                        holder.lyt_parent
                    )
                parents.get(position).get(position).periods.get(position).expanded = show
            })

        }


        // void recycling view
        // void recycling view
        if (p.get(position).periods.get(position).expanded) {
            holder.lyt_expand.setVisibility(View.VISIBLE)
        } else {
            holder.lyt_expand.setVisibility(View.GONE)
        }
        viewAnimation.toggleArrow(
            p.get(position).periods.get(position).expanded,
            holder.btn_expand,
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


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recyclerView: RecyclerView = itemView.rv_child
        val periodName: TextView = itemView.tv_name
        val periopName2: TextView = itemView.tv_periodName
        val date: TextView = itemView.tv_date
        val lyt_parent: View = itemView.lyt_parent
        val lyt_expand: View = itemView.lyt_expand
        val btn_expand: ImageButton = itemView.btn_expand


    }
}


