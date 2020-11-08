package com.enjaz.hr.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.enjaz.hr.MainActivity
import com.enjaz.hr.R
import com.enjaz.hr.databinding.ActivityLoginBinding
import com.enjaz.hr.ui.base.BaseActivity
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity :
    BaseActivity<ActivityLoginBinding, ILoginInteractionListener, LoginViewModel>(),
    ILoginInteractionListener {
    private val loginViewModel: LoginViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        form {

            input(R.id.et_password) {
                isNotEmpty().description("Password required")
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    et_password.error = firstError?.description
                }
            }
            input(R.id.et_email) {
                isEmail().description("Enter a valid E-MAIL")
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    et_email.error = firstError?.description
                }
            }
            submitWith(R.id.btn_login) {

                login()
            }
        }
    }

    override fun getNavigator(): ILoginInteractionListener {
        return this
    }

    override fun login() {

    }

    override fun showSnack(string: String, color: String, drawable: Int?) {

    }

    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }

}

interface ILoginInteractionListener : BaseNavigator {
    fun login()
    fun showSnack(string: String, color: String, drawable: Int?)
}
