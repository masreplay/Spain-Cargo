package com.enjaz.hr.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetHomeBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FramgnetHomeBinding, IHomeInteractionListener, HomeViewModel>(),
    IHomeInteractionListener {

    val homeviewModel: HomeViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.framgnet_home
    }

    override fun getViewModel(): HomeViewModel {
        return homeviewModel
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getNavigator(): IHomeInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().mylog()
    }


    override fun showAllClasses() {
        // navigate to classes view ==> findNavController().navigate(R.id.classesFragment)
    }

    override fun showAllTasks() {
        // navigate to tasks view ==> findNavController().navigate(R.id.classesFragment)

    }




}

interface IHomeInteractionListener : BaseNavigator {
    fun showAllClasses()
    fun showAllTasks()
}
