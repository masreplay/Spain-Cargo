package com.enjaz.hr.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.vvalidator.util.hide
import com.enjaz.hr.R
import com.enjaz.hr.data.model.salary.Item
import com.enjaz.hr.databinding.FragmentDeductionDetailsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeductionDetailsFragment :
    BaseFragment<FragmentDeductionDetailsBinding, IProfileInteractionListener, ProfileViewModel>(),
    IProfileInteractionListener {

    private val profileViewModel: ProfileViewModel by viewModels()
    private val args: DeductionDetailsFragmentArgs by navArgs()

    lateinit var salaryDetailAdapter: SalaryDetailAdapter
    lateinit var salaryDetailAdapterDed: SalaryDetailAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_deduction_details

    }

    override fun getViewModel(): ProfileViewModel {
        return profileViewModel
    }


    override fun getNavigator(): IProfileInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        salaryDetailAdapter= SalaryDetailAdapter(requireContext(), arrayListOf())
        salaryDetailAdapterDed= SalaryDetailAdapter(requireContext(), arrayListOf())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson = Gson()
        val res=gson.fromJson(args.salaryDetails, Item::class.java)

        getViewDataBinding().rvAdditions.apply {
            adapter=salaryDetailAdapter
        }

        getViewDataBinding().rvDeductions.apply {
            adapter=salaryDetailAdapterDed
        }

        if(res.salaryDetails.none { !it.isAddition })
            getViewDataBinding().lytDeductions.hide()

        salaryDetailAdapter.setItems(res.salaryDetails.filter { it.isAddition })
        salaryDetailAdapterDed.setItems(res.salaryDetails.filter { !it.isAddition })

    }


    override fun onPersonalDetailsClick() {
    }

    override fun onDeleteProfilePhotoClick() {
        TODO("Not yet implemented")
    }

    override fun onBalanceClick() {
    }

    override fun onSalaryDetailsClick() {
    }

    override fun onSettingsClick() {
    }

    override fun onEditProfilePhotoClick() {
        TODO("Not yet implemented")
    }

    override fun detailsAvailable() {
    }

    override fun noDetails() {
    }

    override fun hideLeaveCreditView() {
    }

    override fun showLeaveCreditView() {
    }


}


