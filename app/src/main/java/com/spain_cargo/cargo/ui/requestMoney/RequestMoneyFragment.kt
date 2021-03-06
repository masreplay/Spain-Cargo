package com.spain_cargo.cargo.ui.requestMoney

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.Status
import com.spain_cargo.cargo.data.model.login.User.Companion.ADMIN
import com.spain_cargo.cargo.data.model.login.User.Companion.DISTRIBUTOR
import com.spain_cargo.cargo.data.model.login.User.Companion.IMMEDIATE
import com.spain_cargo.cargo.data.model.login.User.Companion.NORMAL
import com.spain_cargo.cargo.data.model.login.User.Companion.USER
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

    private lateinit var usersAdapter: ArrayAdapter<String>
    private lateinit var typesAdapter: ArrayAdapter<String>

    private val users = listOf(USER, DISTRIBUTOR, ADMIN)
    private val types = listOf(IMMEDIATE, NORMAL)

    override fun getLayoutId() = R.layout.fragment_request_money
    override fun getViewModel() = addItemViewModel
    override fun getNavigator() = this


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().requestMoneyResponse.observe(requireActivity()) { resource ->
            resource?.let {
                if (it.status == Status.LOADING) {
                    getViewDataBinding().progressCircular.show()
                }
                if (it.status == Status.SUCCESS) {
                    getViewDataBinding().progressCircular.hide()
                    requireActivity().toast(R.string.money_success)
                }
                if (it.status == Status.ERROR) {
                    getViewDataBinding().progressCircular.hide()
                    requireActivity().toast(R.string.failed_to_request_money)
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
        if (et_from.text.toString() == USER && et_key.text.isNullOrEmpty()) {
            getViewDataBinding().tilKey.error = getString(R.string.msg_err_field_required)
        } else if (et_from.text.toString() != USER) {
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