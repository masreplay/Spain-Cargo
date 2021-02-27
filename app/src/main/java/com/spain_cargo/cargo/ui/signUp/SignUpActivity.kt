package com.spain_cargo.cargo.ui.signUp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.net.toFile
import androidx.lifecycle.Observer
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.Status
import com.spain_cargo.cargo.databinding.ActivitySignupBinding
import com.spain_cargo.cargo.ui.base.BaseActivity
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.ui.countries.CountriesActivity
import com.spain_cargo.cargo.util.UploadRequestBody
import com.spain_cargo.cargo.util.toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MultipartBody
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class SignUpActivity :
    BaseActivity<ActivitySignupBinding, ILoginInteractionListener, SignUpViewModel>(),
    ILoginInteractionListener {

    private val loginViewModel: SignUpViewModel by viewModels()

    private var newImageUri: Uri? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_signup
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getViewModel().signupResponse.observe(this, Observer { resource ->
            resource?.let {
                if (it.status == Status.SUCCESS) {
                    getViewDataBinding().progressCircular.hide()
                    startActivity(Intent(this@SignUpActivity, CountriesActivity::class.java))
                    finish()
                }
                if (it.status == Status.ERROR) {
                    getViewDataBinding().progressCircular.hide()
                    this.toast(R.string.msg_err_login)
                }
                if (it.status == Status.LOADING) {
                    getViewDataBinding().progressCircular.show()
                }
            }
        })


        getViewDataBinding().imageView.setOnClickListener {
            val intent = CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .getIntent(this)
            startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        }

        getViewDataBinding().etDate.setOnClickListener {
            dialogDatePickerLightStart()
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
            input(R.id.et_password) {
                isNotEmpty().description(getString(R.string.msg_err_password))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilPassword.error = firstError?.description
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

            submitWith(R.id.btn_login) {
                signUp()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri: Uri = result.uri
                newImageUri = resultUri
                getViewDataBinding().imageView.setImageURI(newImageUri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

    fun signUp() {


        if (newImageUri != null) {
            getViewModel().signUp(
                MultipartBody.Part.createFormData(
                    "image",
                    newImageUri?.toFile()?.name,
                    UploadRequestBody(
                        newImageUri?.toFile()!!,
                        "image",
                        "png"
                    )
                )
                ,
                getViewDataBinding().etName.text.toString(),
                getViewDataBinding().etEmail.text.toString(),
                getViewDataBinding().etPassword.text.toString(),
                getViewDataBinding().etPhone.text.toString(),
                getViewDataBinding().etDate.text.toString()
            )
        } else {
            this.toast(R.string.msg_err_image)
        }
    }

    override fun getNavigator(): ILoginInteractionListener {
        return this
    }

    override fun getViewModel(): SignUpViewModel {
        return loginViewModel
    }

    fun dialogDatePickerLightStart() {
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
            this,
            timeSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        pickerDialog.datePicker.maxDate = maxDate.timeInMillis
        pickerDialog.show()

    }

}

interface ILoginInteractionListener : BaseNavigator
