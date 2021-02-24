package com.spain_cargo.cargo.util

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.spain_cargo.cargo.SpainApp
import com.spain_cargo.cargo.data.model.login.MainResponse


class PrefsManager {
    private var context = SpainApp
    private var sharedPref: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context.applicationContext())
    private lateinit var sharedPrefsEditor: SharedPreferences.Editor


    fun isLoggedIn(): Boolean {
        return getUser() != null
    }

    fun clearPreferences(action: () -> Unit) {
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.clear()
        sharedPrefsEditor.apply()
        action.invoke()
    }

    fun getUser(): MainResponse? {
        val gson = Gson()
        return gson.fromJson(
            sharedPref.getString("login_response", null),
            MainResponse::class.java
        )
    }


    fun saveUser(profile: MainResponse) {
        val gson = Gson()
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putString("login_response", gson.toJson(profile))
        sharedPrefsEditor.apply()
    }



    companion object {

        var instance: PrefsManager? = null
            private set

        fun init() {
            if (instance == null) {
                instance = PrefsManager()
            }
        }
    }
}
