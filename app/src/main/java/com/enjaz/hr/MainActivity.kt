package com.enjaz.hr

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.enjaz.hr.ui.login.LoginActivity
import com.enjaz.hr.util.PrefsManager
import com.enjaz.hr.util.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!PrefsManager.instance?.isLoggedIn()!!){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
        }


        val sharedPreferences = getDefaultSharedPreferences(applicationContext)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)


        Lingver.getInstance().setLocale(this,
            getDefaultSharedPreferences(applicationContext).getString("language", "en")!!
        )


    }


    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val navGraphIds = listOf(
            R.navigation.home,
            R.navigation.requests,
            R.navigation.notifications,
            R.navigation.attendance,
            R.navigation.profile,
            R.navigation.requests
        )

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        controller.observe(this, Observer { navController ->

            navController.addOnDestinationChangedListener { controller, destination, arguments ->

                setupActionBarWithNavController(controller)

                when (destination.id) {
                    R.id.sendRequestFragment -> {
                        bottomNavigationView.hide()
                    }
                    else -> bottomNavigationView.show()

                }

            }

        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, key: String?) {
        if (key.equals("language")) {
            Lingver.getInstance().setLocale(this, p0!!.getString(key, "en")!!)
            val intent=Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }

    fun refreshRequestsFragment(){
        
    }
}
