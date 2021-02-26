package com.spain_cargo.cargo.ui.moneyRBCreate

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.Status
import com.spain_cargo.cargo.databinding.FragmentMoneyRbCreateBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.util.PrefsManager
import com.spain_cargo.cargo.util.print
import com.spain_cargo.cargo.util.toEditable
import com.spain_cargo.cargo.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoneyRBCreateFragment :
    BaseFragment<FragmentMoneyRbCreateBinding, ICreateMoneyRBInteractionListener, MoneyRBCreateViewModel>(),
    ICreateMoneyRBInteractionListener {

    private val addItemViewModel: MoneyRBCreateViewModel by viewModels()

    private val users = listOf("distributor", "admin")

    private lateinit var usersAdapter: ArrayAdapter<String>

    override fun getLayoutId() = R.layout.fragment_money_rb_create
    override fun getViewModel() = addItemViewModel
    override fun getNavigator() = this

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, users)
        (getViewDataBinding().tilFrom.editText as? AutoCompleteTextView)?.setAdapter(usersAdapter)

        getViewDataBinding().apply {
            btnAllBalance.setOnClickListener {
                etAmount.text =
                    PrefsManager.instance?.getProfile()?.data?.user?.balance?.amount.toString()
                        .toEditable()
            }

        }

        form {
            input(R.id.et_amount) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
                isNumber().atMost(
                    PrefsManager.instance?.getProfile()?.data?.user?.balance?.amount?.toLong()
                        ?: 1000000
                ).atLeast(1)
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilAmount.error = firstError?.description
                }
            }

            submitWith(R.id.btn_request) {
                createRequest()
            }
        }
    }

    private fun createRequest() {
        getViewDataBinding().etFrom.apply {
            text.toString().print()
            usersAdapter.getPosition(text.toString()).print()

            if (usersAdapter.getPosition(text.toString()) + 1 == 0) {
                getViewDataBinding().tilFrom.error = getString(R.string.pick_an_item)
            }
        }
        getViewModel().moneyBackRequests(
            from = getViewDataBinding().etFrom.text.toString(),
            amount = getViewDataBinding().etAmount.text.toString().toInt()
        )

        getViewModel().requestMoneyResponse.observe(requireActivity()) { resource ->
            resource?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        findNavController().popBackStack()
                    }
                    Status.ERROR -> {
                        requireActivity().toast(R.string.msg_err_login)
                    }
                    else -> {
                        requireActivity().toast(R.string.msg_err_login)
                    }
                }
            }
        }


    }


}

interface ICreateMoneyRBInteractionListener : BaseNavigator