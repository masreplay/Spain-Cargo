package com.enjaz.university.ui.home

import com.enjaz.university.R
import com.enjaz.university.databinding.ActivityLoginBinding
import com.enjaz.university.databinding.FramgnetHomeBinding
import com.enjaz.university.ui.base.BaseFragment
import com.enjaz.university.ui.base.BaseNavigator
import com.enjaz.university.ui.schedual.ScheduleViewModel

class HomeFragment : BaseFragment<FramgnetHomeBinding, IHomeInteractionListener, HomeViewModel>(),IHomeInteractionListener {
    override fun getLayoutId(): Int {
        return R.layout.framgnet_home
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
    return HomeViewModel::class.java
    }

    override fun getNavigator(): IHomeInteractionListener {
        return this
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
