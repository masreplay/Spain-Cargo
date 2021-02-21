package com.spain_cargo.cargo.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.Status
import com.spain_cargo.cargo.databinding.ActivityLoginBinding
import com.spain_cargo.cargo.ui.base.BaseActivity
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.ui.countries.CountriesActivity
import com.spain_cargo.cargo.ui.signUp.SignUpActivity
import com.spain_cargo.cargo.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity :
    BaseActivity<ActivityLoginBinding, ILoginInteractionListener, LoginViewModel>(),
    ILoginInteractionListener {

    private val loginViewModel: LoginViewModel by viewModels()


    override fun getLayoutId() = R.layout.activity_login


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        getViewDataBinding().tvCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        getViewModel().loginResponse.observe(this, Observer { resource ->
            resource?.let {
                if (it.status == Status.LOADING) {
                    getViewDataBinding().progressCircular.show()
                }
                if (it.status == Status.SUCCESS) {
                    getViewDataBinding().progressCircular.hide()
                    startActivity(Intent(this@LoginActivity, CountriesActivity::class.java))
                }
                if (it.status == Status.ERROR) {
                    getViewDataBinding().progressCircular.hide()
                    this.toast(R.string.msg_err_login)
                }
            }
        })


        form {
            input(R.id.et_password) {
                isNotEmpty().description(getString(R.string.msg_err_password))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilPassword.error = firstError?.description
                }
            }
            input(R.id.et_email) {
                isNotEmpty().description(getString(R.string.msg_error_email))
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilEmail.error = firstError?.description
                }
            }
            submitWith(R.id.btn_login) {
                getViewModel().login()
            }
        }
    }

    override fun getNavigator(): ILoginInteractionListener {
        return this
    }

    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }

}

interface ILoginInteractionListener : BaseNavigator
