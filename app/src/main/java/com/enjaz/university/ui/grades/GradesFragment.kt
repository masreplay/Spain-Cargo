package com.enjaz.university.ui.grades

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.enjaz.university.R
import com.enjaz.university.databinding.FragmentGradesBinding
import com.enjaz.university.ui.base.BaseFragment
import com.enjaz.university.ui.base.BaseNavigator

class GradesFragment :
    BaseFragment<FragmentGradesBinding, IGradesInteractionListener, GradesViewModel>(),
    IGradesInteractionListener {

    lateinit var gradesAdapter: GradesAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_grades

    }

    override fun getViewModelClass(): Class<GradesViewModel> {
        return GradesViewModel::class.java
    }

    override fun getNavigator(): IGradesInteractionListener {

        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gradesAdapter = GradesAdapter(activity!!, mutableListOf())


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().getGrades()



        getViewDataBinding().rvGrades.adapter = gradesAdapter
        getViewDataBinding().rvGrades.layoutManager = LinearLayoutManager(activity)


    }

}

interface IGradesInteractionListener : BaseNavigator {


}