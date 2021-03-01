package com.spain_cargo.cargo.ui.profile

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.Status
import com.spain_cargo.cargo.databinding.FragmentProfileUpdateBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.util.PrefsManager
import com.spain_cargo.cargo.util.copyToClipBoard
import com.spain_cargo.cargo.util.toEditable
import com.spain_cargo.cargo.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ProfileUpdateFragment :
    BaseFragment<FragmentProfileUpdateBinding, IProfileUpdateInteractionListener, ProfileUpdateViewModel>(),
    IProfileUpdateInteractionListener {

    private val profileUpdateViewModel: ProfileUpdateViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_profile_update
    override fun getViewModel() = profileUpdateViewModel
    override fun getNavigator() = this


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().apply {
            PrefsManager.instance?.getProfile()?.data?.apply {
                etEmail.text = user.email.toEditable()
                etDate.text = user.dateOfBirth.toEditable()
                etName.text = user.name.toEditable()
                etPhone.text = user.phoneNumber.toEditable()
            }

            btnUpdateProfile.setOnClickListener {
                updateProfile()
            }
            etDate.setOnClickListener {
                dialogDatePickerLightStart()

            }
        }
        form {
            input(R.id.et_name) {
                isNotEmpty().description(getString(R.string.msg_err_name))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilName.error = firstError?.description
                }
            }
            input(R.id.et_email) {
                isNotEmpty().description(getString(R.string.msg_err_email))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilEmail.error = firstError?.description
                }
            }
            input(R.id.et_phone) {
                isNotEmpty().description(getString(R.string.msg_err_phone))
                matches("07[3-9][0-9]{8}").description(getString(R.string.enter_valid_phone))

                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilPhone.error = firstError?.description
                }
            }

            input(R.id.et_date) {
                isNotEmpty().description(getString(R.string.msg_err_date))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilDate.error = firstError?.description
                }
            }

            submitWith(getViewDataBinding().btnUpdateProfile.id) {
                updateProfile()
            }
        }
    }

    override fun copy(key: String) {
        this.copyToClipBoard(key)
    }

    private fun updateProfile() {
        getViewModel().updateProfile(
            getViewDataBinding().etName.text.toString(),
            getViewDataBinding().etEmail.text.toString(),
            getViewDataBinding().etPhone.text.toString(),
            getViewDataBinding().etDate.text.toString()
        )
        getViewModel().updateProfileResponse.observe(viewLifecycleOwner) { resource ->
            resource?.let {
                if (it.status == Status.LOADING) {
                    getViewDataBinding().progressCircular.show()
                }
                if (it.status == Status.SUCCESS) {
                    getViewDataBinding().progressCircular.hide()
                    findNavController().popBackStack()
                }
                if (it.status == Status.ERROR) {
                    getViewDataBinding().progressCircular.hide()
                    requireContext().toast(R.string.msg_err_update)
                }
            }
        }
    }

    private fun dialogDatePickerLightStart() {
        val cal = Calendar.getInstance()
        val timeSetListener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

            cal.set(Calendar.YEAR, i)
            cal.set(Calendar.MONTH, i2)
            cal.set(Calendar.DAY_OF_MONTH, i3)

            getViewDataBinding().etDate.setText(
                SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.US
                ).format(cal.time)
            )
        }

        val maxDate = Calendar.getInstance()
        maxDate.set(2020, 1, 31)
        val pickerDialog = DatePickerDialog(
            requireContext(),
            timeSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        pickerDialog.datePicker.maxDate = maxDate.timeInMillis
        pickerDialog.show()
    }
}

interface IProfileUpdateInteractionListener : BaseNavigator {
    fun copy(key: String)
}