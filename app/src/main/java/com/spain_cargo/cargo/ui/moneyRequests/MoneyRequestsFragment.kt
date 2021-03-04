package com.spain_cargo.cargo.ui.moneyRequests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.moneyRequests.MoneyRequest
import com.spain_cargo.cargo.data.model.moneyRequests.MoneyRequests
import com.spain_cargo.cargo.databinding.FragmentMoneyRequestsBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoneyRequestsFragment : BaseFragment<FragmentMoneyRequestsBinding, IMoneyRequestsInteractionListener, MoneyRequestsViewModel>(),
    IMoneyRequestsInteractionListener, IRequestItemActionListener {

    private val moneyRequestsViewModel: MoneyRequestsViewModel by viewModels()

    private lateinit var moneyRequestsAdapter: MoneyRequestsAdapter
    var requests = mutableListOf<MoneyRequest>()

    private var maxSkip: Int = 0
    var page: Int = 1
    var isLoading = false

    override fun getLayoutId() = R.layout.fragment_money_requests
    override fun getViewModel() = moneyRequestsViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getMoneyRequests(page)
        moneyRequestsAdapter = MoneyRequestsAdapter(requireContext(), requests)
        moneyRequestsAdapter.setOnItemClickListener(this)
        moneyRequestsAdapter.setHasStableIds(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        getViewDataBinding().srl.apply {
            setOnRefreshListener {
                getViewModel().getMoneyRequests(page)
                isRefreshing = false
            }
        }

        val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        getViewDataBinding().rv.apply {
            adapter = moneyRequestsAdapter
            layoutManager = lm
        }


        getViewDataBinding().rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                // FIXME: change skip to page
                val visibleItemCount: Int = lm.childCount
                val totalItemCount: Int = lm.itemCount
                val firstVisibleItemPosition: Int = lm.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && page + 1 <= maxSkip
                    && !isLoading
                ) {
                    isLoading = true
                    page += 1

                    getViewModel().getMoneyRequests(
                        page = page
                    )

                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }


    override fun onItemClick(item: MoneyRequest) {}

    override fun onAccept(item: MoneyRequest) {
        getViewModel().acceptMoneyRequest(item.id.toString())
    }

    override fun onDecline(item: MoneyRequest) {
        getViewModel().rejectMoneyRequest(item.id.toString())
    }

    override fun onSuccess(moneyRequests: MoneyRequests) {
        maxSkip = moneyRequests.data?.pagination?.count!!
        moneyRequests.data.moneyRequests?.let { requests.addAll(it) }
        moneyRequestsAdapter.setItemsList(requests)
        isLoading = false
    }


}

interface IMoneyRequestsInteractionListener : BaseNavigator {
    fun onSuccess(moneyRequests: MoneyRequests)
}