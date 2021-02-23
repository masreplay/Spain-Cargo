package com.spain_cargo.cargo.ui.requestMoney

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
import com.spain_cargo.cargo.databinding.FragmentRequestMoneyBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.util.print
import com.spain_cargo.cargo.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_request_money.*


@AndroidEntryPoint
class RequestMoneyFragment :
    BaseFragment<FragmentRequestMoneyBinding, ICreateOrderInteractionListener, RequestMoneyViewModel>(),
    ICreateOrderInteractionListener {

    private val addItemViewModel: RequestMoneyViewModel by viewModels()

    val users = listOf("user", "distributor", "admin")
    private lateinit var usersAdapter: ArrayAdapter<String>

    val types = listOf("immediate", "normal")
    private lateinit var typesAdapter: ArrayAdapter<String>

    override fun getLayoutId(): Int {
        return R.layout.fragment_request_money
    }

    override fun getViewModel(): RequestMoneyViewModel {
        return addItemViewModel
    }


    override fun getNavigator(): ICreateOrderInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().requestMoneyResponse.observe(requireActivity()) { resource ->
            resource?.let {
                if (it.status == Status.LOADING) {
                }
                if (it.status == Status.SUCCESS) {
                    requireActivity().toast(R.string.money_success)
                    findNavController().popBackStack()
                }
                if (it.status == Status.ERROR) {

                }
            }
        }

        usersAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, users)
        (til_from.editText as? AutoCompleteTextView)?.setAdapter(usersAdapter)

        typesAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, types)
        (til_type.editText as? AutoCompleteTextView)?.setAdapter(typesAdapter)


        form {
            input(R.id.et_amount) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
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
        et_from.text.toString().print()
        et_type.text.toString().print()
        usersAdapter.getPosition(et_from.text.toString()).print()

        if (usersAdapter.getPosition(et_from.text.toString()) + 1 == 0) {
            getViewDataBinding().tilFrom.error = getString(R.string.pick_an_item)
        } else if (typesAdapter.getPosition(et_type.text.toString()) + 1 == 0) {
            getViewDataBinding().tilType.error = getString(R.string.pick_an_item)
        }
        if (et_from.text.toString() == "user" && et_key.text.isNullOrEmpty()) {
            getViewDataBinding().tilKey.error = getString(R.string.msg_err_field_required)
        } else if (et_from.text.toString() != "user") {
            getViewModel().moneyRequest(
                from = et_from.text.toString(),
                amount = et_amount.text.toString().toInt(),
                type = et_type.text.toString()
            )
        } else {
            getViewModel().moneyRequest(
                from = et_from.text.toString(),
                amount = et_amount.text.toString().toInt(),
                type = et_type.text.toString(),
                payment_key = et_key.text.toString()
            )
        }
    }


}

interface ICreateOrderInteractionListener : BaseNavigator