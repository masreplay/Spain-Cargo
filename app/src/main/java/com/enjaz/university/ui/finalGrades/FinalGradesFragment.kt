package com.enjaz.university.ui.finalGrades

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.university.R
import com.enjaz.university.databinding.FragmentFinalGradesBinding
import com.enjaz.university.ui.base.BaseFragment
import com.enjaz.university.ui.base.BaseNavigator

class FinalGradesFragment :
    BaseFragment<FragmentFinalGradesBinding, IFinalGradesInteractionListener, FinalGradesViewModel>(),
    IFinalGradesInteractionListener {

//    lateinit var gradesParentAdapter2: GradesParentAdapter2


    override fun getLayoutId(): Int {
        return R.layout.fragment_final_grades

    }

    override fun getViewModelClass(): Class<FinalGradesViewModel> {
        return FinalGradesViewModel::class.java
    }

    override fun getNavigator(): IFinalGradesInteractionListener {

        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        gradesParentAdapter2= GradesParentAdapter2(activity!!, mutableListOf())


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().getGrades()


//            getViewDataBinding().rvParent.adapter=gradesParentAdapter2
//            getViewDataBinding().rvParent.layoutManager=LinearLayoutManager(activity)
//


        getViewDataBinding().rvParent.apply {
            layoutManager = LinearLayoutManager(
                activity,
                RecyclerView.VERTICAL, false
            )
            adapter = getViewModel().getGradesResponse.value?.data?.result?.let {
                FinalGradesParentAdapter(
                    activity!!,
                    mutableListOf(it)
                )
            }

        }


    }


}

interface IFinalGradesInteractionListener : BaseNavigator {


}