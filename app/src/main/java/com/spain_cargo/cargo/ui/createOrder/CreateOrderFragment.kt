package com.spain_cargo.cargo.ui.createOrder

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.CreateOrder
import com.spain_cargo.cargo.data.model.Item
import com.spain_cargo.cargo.data.model.Status
import com.spain_cargo.cargo.databinding.FragmentCreateOrderBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.util.Constants.country
import com.spain_cargo.cargo.util.PrefsManager
import com.spain_cargo.cargo.util.print
import com.spain_cargo.cargo.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_order.*


@AndroidEntryPoint
class CreateOrderFragment :
    BaseFragment<FragmentCreateOrderBinding, ICreateOrderInteractionListener, CreateOrderViewModel>(),
    ICreateOrderInteractionListener, IItemActionListener {

    private val createOrderViewModel: CreateOrderViewModel by viewModels()
    lateinit var locations: MutableList<String>
    lateinit var items: List<Item>

    private lateinit var spinnerAdapter: ArrayAdapter<String>

    override fun getLayoutId() = R.layout.fragment_create_order
    override fun getViewModel() = createOrderViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locations = mutableListOf()
        items = mutableListOf()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().getCities()
        getViewModel().getItems()

        getViewDataBinding().etPhone.setText(PrefsManager.instance?.getUser()?.data?.user?.phoneNumber)

        getViewDataBinding().btnAddItem.setOnClickListener {
            findNavController().navigate(R.id.addItemFragment)
        }

        getViewModel().checkoutResponse.observe(requireActivity()) { resource ->
            resource?.let {
                it.status.print()
                if (it.status == Status.LOADING) {
                    getViewDataBinding().progressCircular.show()
                }
                if (it.status == Status.SUCCESS) {
                    getViewDataBinding().progressCircular.hide()
                    findNavController().popBackStack()
                }
                if (it.status == Status.ERROR) {
                    getViewDataBinding().progressCircular.hide()
                    requireActivity().toast(R.string.failed_to_checkout)
                }
            }
        }

        getViewModel().citiesResponse.observe(requireActivity(), Observer {

            locations.clear()
            it.data?.data?.cities?.forEach { city ->
                locations.add(city.name!!)
            }

            spinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, locations)
            (til_city.editText as? AutoCompleteTextView)?.setAdapter(spinnerAdapter)

        })

        getViewModel().itemsResponse.observe(requireActivity(), Observer {
            items = it
            items.print()
        })

        val itemsAdapter = ItemsAdapter(requireActivity(), mutableListOf())
        itemsAdapter.setOnItemClickListener(this)

        getViewDataBinding().rv.apply {
            adapter = itemsAdapter
        }

        form {
            input(R.id.et_address) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilAddress.error = firstError?.description
                }
            }
            input(R.id.et_phone) {
                isNotEmpty().description(getString(R.string.msg_err_field_required))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilPhone.error = firstError?.description
                }
            }
            submitWith(R.id.btn_checkout) {
                createOrder()
            }
        }


    }

    override fun onDeleteClick(item: Item) {
        getViewModel().deleteItem(item)
        getViewModel().getItems()
    }

    private fun createOrder() {
        if (spinnerAdapter.getPosition(et_cities.text.toString()) + 1 == 0) {
            getViewDataBinding().tilCity.error = getString(R.string.pick_location)
        } else {
            getViewModel().checkout(
                CreateOrder(
                    getViewDataBinding().etAddress.text.toString(),
                    getViewDataBinding().etPhone.text.toString(),
                    getViewModel().citiesResponse.value?.data?.data?.cities?.get(
                        spinnerAdapter.getPosition(et_cities.text.toString())
                    )?.id,
                    country.id,
                    items
                )

            )
        }

    }


}

interface ICreateOrderInteractionListener : BaseNavigator