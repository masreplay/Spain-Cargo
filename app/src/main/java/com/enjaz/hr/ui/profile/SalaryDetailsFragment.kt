package com.enjaz.hr.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.hr.R
import com.enjaz.hr.data.model.salary.Item
import com.enjaz.hr.databinding.FragmentSalaryDetailsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.util.makeGone
import com.enjaz.hr.util.makeVisible
import com.google.gson.Gson
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

        getViewModel().getSalaryDetails()

        getViewDataBinding().rv.apply {
            layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
            adapter=salaryDetailsAdapter
        }


    }

    override fun detailsAvailable() {
        getViewDataBinding().lytNoData.makeGone()
    }

    override fun noDetails() {
        getViewDataBinding().lytNoData.makeVisible()
    }


    override fun onItemClick(model: Item) {
        val gson=Gson()
        val action =SalaryDetailsFragmentDirections.nextAction(gson.toJson(model))
        findNavController().navigate(action)
    }


    override fun onPersonalDetailsClick() {
        TODO("Not yet implemented")
    }

    override fun onDeleteProfilePhotoClick() {
        TODO("Not yet implemented")
    }

    override fun onBalanceClick() {
        TODO("Not yet implemented")
    }

    override fun onSalaryDetailsClick() {
        TODO("Not yet implemented")
    }

    override fun onSettingsClick() {
        TODO("Not yet implemented")
    }

    override fun onEditProfilePhotoClick() {
        TODO("Not yet implemented")
    }


    override fun hideLeaveCreditView() {
        TODO("Not yet implemented")
    }

    override fun showLeaveCreditView() {
        TODO("Not yet implemented")
    }


}


