package com.enjaz.hr.ui.sendRequest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.hr.R
import com.enjaz.hr.data.model.Types
import com.enjaz.hr.databinding.FragmentRequsetMainTypesBinding
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.ui.base.BaseSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestMainTypesFragment :
    BaseSheetFragment<FragmentRequsetMainTypesBinding, IRequestTypesInteractionListener, RequestTypesViewModel>(),
    IRequestTypesInteractionListener ,ITypeItemActionListener{

    private val sedRequestViewModel: RequestTypesViewModel by viewModels()
    lateinit var requsetTypesAdapter: RequsetMainTypesAdapter

    lateinit var list:ArrayList<String>

    override fun getLayoutId(): Int {
        return R.layout.fragment_requset_main_types
    }

    override fun getViewModel(): RequestTypesViewModel {
        return sedRequestViewModel
    }


    override fun getNavigator(): IRequestTypesInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requsetTypesAdapter = RequsetMainTypesAdapter(requireContext(), mutableListOf())
        requsetTypesAdapter.setOnItemClickListener(this)

        list= arrayListOf()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().getData()

        getViewDataBinding().rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = requsetTypesAdapter

        }


    }

    override fun onTypeClick(type: Types) {

            val action =
                RequestMainTypesFragmentDirections.nextAction(
                    type.type
                )
            findNavController().navigate(action)

    }



}
interface IRequestTypesInteractionListener : BaseNavigator {


}



