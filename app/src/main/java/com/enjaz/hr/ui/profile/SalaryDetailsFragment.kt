package com.enjaz.hr.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentSalaryDetailsBinding
import com.enjaz.hr.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SalaryDetailsFragment :
    BaseFragment<FragmentSalaryDetailsBinding, IProfileInteractionListener, ProfileViewModel>(),
    IProfileInteractionListener ,ISalartDetailsItemActionListener{

    private val profileViewModel: ProfileViewModel by viewModels()


    lateinit var salaryDetailsAdapter: SalaryDetailsAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_salary_details

    }

    override fun getViewModel(): ProfileViewModel {
        return profileViewModel
    }



    override fun getNavigator(): IProfileInteractionListener {
        return this
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        salaryDetailsAdapter= SalaryDetailsAdapter(requireContext(), arrayListOf())
        salaryDetailsAdapter.setOnItemClickListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().mylog()
        salaryDetailsAdapter.setItems(getViewModel().loginResponse)
        getViewDataBinding().rv.apply {
            layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
            adapter=salaryDetailsAdapter
        }


    }



    override fun onPersonalDetailsClick() {
        TODO("Not yet implemented")
    }

    override fun onSalaryDetailsClick() {
        TODO("Not yet implemented")
    }

    override fun onItemClick(model: String) {
        findNavController().navigate(R.id.deductionDetailsFragment)
    }


}


