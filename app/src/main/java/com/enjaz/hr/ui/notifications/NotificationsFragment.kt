package com.enjaz.hr.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.hr.R
import com.enjaz.hr.data.model.Data
import com.enjaz.hr.databinding.FragmentNotificationsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding, INotificationsInteractionListener, NotificationsViewModel>(),
    INotificationsInteractionListener, INotificationItemActionListener {

    private val notificationsViewModel: NotificationsViewModel by viewModels()

    lateinit var notificationAdapter: NotificationsAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_notifications
    }

    override fun getViewModel(): NotificationsViewModel {
        return notificationsViewModel
    }

    override fun getViewModelClass(): Class<NotificationsViewModel> {
        return NotificationsViewModel::class.java
    }

    override fun getNavigator(): INotificationsInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationAdapter = NotificationsAdapter(requireContext(), mutableListOf())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationAdapter.setOnItemClickListener(this)

        getViewModel().getdata(10)

        getViewModel().notificationsResponse.value?.let {
            notificationAdapter.setItems(it)
        }

        val mLayoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        getViewDataBinding().rv.apply {
            layoutManager = mLayoutManager
            adapter = notificationAdapter
        }
}




     override fun onNotificationClick(string: Data) {
//        findNavController().navigate(R.id.sendRequestFragment)
    }

//    private fun loadNextPage(data:MutableList<Data>) {
//        isLoading = false // 3
//        notificationAdapter.add(data) // 4
//        if (currentPage == TOTAL_PAGES) isLastPage = true
//    }

}


interface INotificationsInteractionListener : BaseNavigator
