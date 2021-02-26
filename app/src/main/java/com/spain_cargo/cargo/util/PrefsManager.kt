package com.spain_cargo.cargo.util

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.spain_cargo.cargo.SpainApp
import com.spain_cargo.cargo.data.model.login.MainResponse
import com.spain_cargo.cargo.data.model.profile.ProfileResponse


class PrefsManager {
    private var context = SpainApp
    private var sharedPref: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context.applicationContext())
    private lateinit var sharedPrefsEditor: SharedPreferences.Editor


    fun isLoggedIn(): Boolean {
        return getUser() != null
    }

    fun clearPreferences(action: (() -> Unit)? = null) {
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.clear()
        sharedPrefsEditor.apply()
        action?.invoke()
    }

    fun saveUser(profile: MainResponse) {
        val gson = Gson()
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putString(LOGIN_KEY, gson.toJson(profile))
        sharedPrefsEditor.apply()
    }

    fun getUser(): MainResponse? {
        val gson = Gson()
        return gson.fromJson(
            sharedPref.getString(LOGIN_KEY, null),
            MainResponse::class.java
        )
    }

    fun saveProfile(profile: ProfileResponse) {
        val gson = Gson()
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putString(PROFILE_KEY, gson.toJson(profile))
        sharedPrefsEditor.apply()
    }

    fun getProfile(): ProfileResponse? {
        val gson = Gson()
        return gson.fromJson(
            sharedPref.getString(PROFILE_KEY, null),
            ProfileResponse::class.java
        )
    }

    companion object {

        var instance: PrefsManager? = null
            private set

        fun init() {
            if (instance == null) {
                instance = PrefsManager()
            }
        }

        private const val LOGIN_KEY = "login_response"
        private const val PROFILE_KEY = "profile_response"
    }
}
