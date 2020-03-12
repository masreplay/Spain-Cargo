package com.enjaz.university.ui.schedual

import com.enjaz.university.R
import com.enjaz.university.databinding.FragmentScheduleBinding
import com.enjaz.university.ui.base.BaseFragment
import com.enjaz.university.ui.base.BaseNavigator

class ScheduleFragment : BaseFragment<FragmentScheduleBinding, IScheduleInteractionListener, ScheduleViewModel>(),IScheduleInteractionListener {
    override fun getLayoutId(): Int {
        return R.layout.fragment_schedule
    }

    override fun getViewModelClass(): Class<ScheduleViewModel> {
    return ScheduleViewModel::class.java
    }

    override fun getNavigator(): IScheduleInteractionListener {
        return this
    }




}
interface IScheduleInteractionListener : BaseNavigator {

}
