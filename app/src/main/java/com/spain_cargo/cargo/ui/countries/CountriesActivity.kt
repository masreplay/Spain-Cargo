package com.spain_cargo.cargo.ui.countries

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.spain_cargo.cargo.MainActivity
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.countries.Country
import com.spain_cargo.cargo.databinding.ActivityCountiesBinding
import com.spain_cargo.cargo.ui.base.BaseActivity
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.ui.login.LoginActivity
import com.spain_cargo.cargo.util.Constants.country_id
import com.spain_cargo.cargo.util.Constants.RECEIVED_LINK
import com.spain_cargo.cargo.util.PrefsManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesActivity :
    BaseActivity<ActivityCountiesBinding, ICountriesInteractionListener, CountriesViewModel>(),
    ICountriesInteractionListener, INotificationItemActionListener {

    private val countriesViewModel: CountriesViewModel by viewModels()

    override fun getLayoutId() = R.layout.activity_counties

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewModel().getCountries()

        if (!PrefsManager.instance?.isLoggedIn()!!) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val countriesAdapter = CountriesAdapter(this, mutableListOf())
        countriesAdapter.setOnItemClickListener(this)

        getViewDataBinding().rvCountries.apply {
            adapter = countriesAdapter
        }

        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent)
                }
            }
        }
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            RECEIVED_LINK  = it
        }
    }

    override fun getNavigator() = this

    override fun getViewModel() = countriesViewModel

    override fun onItemClick(item: Country) {
        country_id = item.id
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}

interface ICountriesInteractionListener : BaseNavigator
