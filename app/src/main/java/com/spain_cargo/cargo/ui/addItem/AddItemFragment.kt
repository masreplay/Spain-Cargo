package com.spain_cargo.cargo.ui.addItem

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.Item
import com.spain_cargo.cargo.databinding.FragmentAddItemBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddItemFragment :
    BaseFragment<FragmentAddItemBinding, IAddItemInteractionListener, AddItemViewModel>(),
    IAddItemInteractionListener {

    private val addItemViewModel: AddItemViewModel by viewModels()

    override fun getLayoutId(): Int {
        return R.layout.fragment_add_item
    }

    override fun getViewModel(): AddItemViewModel {
        return addItemViewModel
    }


    override fun getNavigator(): IAddItemInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        form {
            input(R.id.et_link) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilLink.error = firstError?.description
                }
            }
            input(R.id.et_size) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilSize.error = firstError?.description
                }
            }
            input(R.id.et_price) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilPrice.error = firstError?.description
                }
            }
            input(R.id.et_color) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilColor.error = firstError?.description
                }
            }
            input(R.id.et_quantity) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilQuantity.error = firstError?.description
                }
            }
            submitWith(R.id.btn_add_item) {
                addItem()

            }
        }

    }

    private fun addItem() {
        getViewModel().insertItem(
            Item(
                url = getViewDataBinding().etLink.text.toString(),
                color = getViewDataBinding().etColor.text.toString(),
                price = getViewDataBinding().etPrice.text.toString().toDouble(),
                quantity = getViewDataBinding().etQuantity.text.toString().toInt(),
                size = getViewDataBinding().etSize.text.toString(),
                notes = getViewDataBinding().etNote.text.toString()
            )
        )
        findNavController().popBackStack()
    }

}

interface IAddItemInteractionListener : BaseNavigator