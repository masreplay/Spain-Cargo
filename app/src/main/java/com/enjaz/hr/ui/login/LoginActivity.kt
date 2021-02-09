package com.enjaz.hr.ui.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.enjaz.hr.MainActivity
import com.enjaz.hr.R
import com.enjaz.hr.data.model.Status
import com.enjaz.hr.databinding.ActivityLoginBinding
import com.enjaz.hr.ui.base.BaseActivity
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.util.PrefsManager
import com.enjaz.hr.util.showSnack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity :
    BaseActivity<ActivityLoginBinding, ILoginInteractionListener, LoginViewModel>(),
    ILoginInteractionListener {
    private val loginViewModel: LoginViewModel by viewModels()

    lateinit var loadingDialog: SweetAlertDialog


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        loadingDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        loadingDialog.titleText = getString(R.string.logging_in)
        loadingDialog.setCancelable(false)

        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        getViewModel().tokenResponse.observe(this, Observer { resource ->
            resource?.let {
                if (it.status == Status.SUCCESS || it.status == Status.ERROR)
                    loadingDialog.hide()
                else
                    loadingDialog.show()
            }
        })

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

    override fun login() {
        PrefsManager.instance?.setLoggedIn()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showError() {
        val contextView = findViewById<View>(R.id.parent)
        contextView.showSnack("Invalid username or password!")
    }

    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }

}

interface ILoginInteractionListener : BaseNavigator {
    fun login()
    fun showError()
}
