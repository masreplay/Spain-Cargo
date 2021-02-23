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
import com.spain_cargo.cargo.util.print
import com.spain_cargo.cargo.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_request_money.*


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, users)
        (til_from.editText as? AutoCompleteTextView)?.setAdapter(usersAdapter)


        form {
            input(R.id.et_amount) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilAmount.error = firstError?.description
                }
            }

            submitWith(R.id.btn_request) {
                getViewDataBinding().etAmount.text.toString().print("123123")
                createRequest()

            }
        }
    }

    private fun createRequest() {
        et_from.text.toString().print()
        usersAdapter.getPosition(et_from.text.toString()).print()

        if (usersAdapter.getPosition(et_from.text.toString()) + 1 == 0) {
            getViewDataBinding().tilFrom.error = getString(R.string.pick_an_item)
        }

        getViewModel().moneyBackRequests(
            from = getViewDataBinding().etFrom.text.toString(),
            amount = getViewDataBinding().etAmount.text.toString().toInt()
        )

        getViewModel().requestMoneyResponse.observe(requireActivity()) { resource ->
            resource?.let {
                if (it.status == Status.LOADING) {
                }
                if (it.status == Status.SUCCESS) {
                    findNavController().popBackStack()

                }
                if (it.status == Status.ERROR) {

                    requireActivity().toast(R.string.msg_err_login)
                }
            }
        }


    }


}

interface ICreateMoneyRBInteractionListener : BaseNavigator