package com.enjaz.university.ui.login

import android.content.Intent
import com.enjaz.university.MainActivity
import com.enjaz.university.R
import com.enjaz.university.databinding.ActivityLoginBinding
import com.enjaz.university.ui.base.BaseActivity
import com.enjaz.university.ui.base.BaseNavigator

class LoginActivity :
    BaseActivity<ActivityLoginBinding, ILoginInteractionListener, LoginViewModel>(),
    ILoginInteractionListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun getNavigator(): ILoginInteractionListener {
        return this
    }

    override fun login() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

interface ILoginInteractionListener : BaseNavigator {
    fun login()
}