package com.enjaz.hr.ui.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.afollestad.vvalidator.util.hide
import com.enjaz.hr.MainActivity
import com.enjaz.hr.R
import com.enjaz.hr.databinding.ActivityLoginBinding
import com.enjaz.hr.ui.base.BaseActivity
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.util.PrefsManager
import com.enjaz.hr.util.snackBar
import dagger.hilt.android.AndroidEntryPoint

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
        getViewDataBinding().status.constraintError.setBackgroundColor(Color.TRANSPARENT)
        getViewDataBinding().status.constraintError.isClickable = false

        getViewDataBinding().status.tvErrorMsg.hide()
        getViewDataBinding().status.imageError.hide()

        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        form {


            input(R.id.et_password) {
                isNotEmpty().description("Password required")
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilPassword.error = firstError?.description
                }
            }
            input(R.id.et_email) {
                isNotEmpty().description("Enter a valid E-MAIL")
                onErrors { _, errors ->
                    val firstError: FieldError? = errors.firstOrNull()
                    getViewDataBinding().tilEmail.error  = firstError?.description
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

    override fun login() {

        PrefsManager.instance?.setString("login", "1")
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 800)
    }

    override fun showSnack(string: String, color: String, drawable: Int?) {

        snackBar(string, drawable, color, getViewDataBinding().parentView, this)

    }

    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }

}

interface ILoginInteractionListener : BaseNavigator {
    fun login()
    fun showSnack(string: String, color: String, drawable: Int?)
}
