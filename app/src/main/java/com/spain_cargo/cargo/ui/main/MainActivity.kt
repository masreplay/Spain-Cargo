package com.spain_cargo.cargo.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.login.User.Companion.USER
import com.spain_cargo.cargo.databinding.ActivityMainBinding
import com.spain_cargo.cargo.ui.base.BaseActivity
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.ui.countries.CountriesActivity
import com.spain_cargo.cargo.util.Constants.country
import com.spain_cargo.cargo.util.PrefsManager
import com.spain_cargo.cargo.util.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, IMainInteractionListener, MainViewModel>(),
    IMainInteractionListener {

    private var currentNavController: LiveData<NavController>? = null

    private val mainViewModel: MainViewModel by viewModels()
    override fun getLayoutId() = R.layout.activity_main
    override fun getViewModel() = mainViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (savedInstanceState == null) {
            setupBottomNavigationBar()
            setSupportActionBar(getViewDataBinding().toolbar)
        }

        getViewDataBinding().fab.setOnClickListener {
            when (currentNavController?.value?.currentDestination?.id) {
                R.id.homeFragment -> {
                    currentNavController?.value?.navigate(R.id.createOrderFragment)
                }
                R.id.fragment_rb_money -> {
                    currentNavController?.value?.navigate(R.id.fragment_rb_create_money)
                }
                else -> {
                    currentNavController?.value?.navigate(R.id.action_request_money_fragment_to_create_request_money_fragment)
                }
            }
        }
        getViewDataBinding().fab2.setOnClickListener {
            currentNavController?.value?.navigate(R.id.action_request_money_fragment_to_home_money_rb_create_fragment)
        }

    }

    override fun onSupportNavigateUp() = currentNavController?.value?.navigateUp() ?: false

    private fun setupBottomNavigationBar() {

        val navGraphIds = if (PrefsManager.instance?.getUser()?.data?.user?.role == USER) {
            getViewDataBinding().bottomNav.menu.removeItem(R.id.request)
            listOf(
                R.navigation.home,
                R.navigation.orders,
                R.navigation.money,
                R.navigation.profile
            )
        } else {
            listOf(
                R.navigation.home,
                R.navigation.orders,
                R.navigation.money,
                R.navigation.request,
                R.navigation.profile
            )
        }

        val controller = getViewDataBinding().bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )


        val intent = Intent(this, CountriesActivity::class.java)
        controller.observe(this) { navController ->
            navController.addOnDestinationChangedListener { controller, destination, _ ->
                setupActionBarWithNavController(controller)

                getViewDataBinding().apply {
                    tvLabel.text = navController.currentDestination?.label
                    ivCountry.setImageURI(country.flagImageUrl)
                    tvCountry.text = country.name
                    llCountry.setOnClickListener {
                        startActivity(intent)
                        finish()
                    }
                }

                when (destination.id) {
                    R.id.homeFragment, R.id.fragment_rb_money -> {
                        getViewDataBinding().apply {
                            llCountry.show()
                            fab.show()
                            fab2.hide()
                            fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_add_24))
                            bottomNav.show()
                        }
                        supportActionBar?.setDisplayShowTitleEnabled(false)

                    }
                    R.id.request_money_fragment -> {
                        getViewDataBinding().apply {
                            fab.show()
                            fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_attach_money_24))
                            fab2.show()
                            bottomNav.show()
                            llCountry.show()
                        }
                    }
                    R.id.ordersFragment, R.id.profile_fragment -> {
                        getViewDataBinding().apply {
                            fab.hide()
                            fab2.hide()
                            llCountry.show()
                            bottomNav.show()
                        }
                    }
                    R.id.createOrderFragment, R.id.addItemFragment, R.id.create_request_money_fragment, R.id.fragment_rb_create_money, R.id.home_money_rb_create_fragment, R.id.order_detail_fragment,R.id.profile_update_fragment -> {
                        getViewDataBinding().apply {
                            fab.hide()
                            fab2.hide()
                            llCountry.hide()
                            bottomNav.hide()
                        }
                    }
                    else -> {
                        getViewDataBinding().apply {
                            fab.hide()
                            fab2.hide()
                            llCountry.hide()
                            bottomNav.show()
                        }
                    }
                }
            }
        }
        currentNavController = controller
    }


}

interface IMainInteractionListener : BaseNavigator