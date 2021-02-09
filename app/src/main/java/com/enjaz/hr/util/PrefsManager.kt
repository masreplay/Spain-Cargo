package com.enjaz.hr.util

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.enjaz.hr.HRMApp
import com.enjaz.hr.data.model.profile.UserInfo
import com.google.gson.Gson


class PrefsManager {
    private var context = HRMApp
    private var sharedPref: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context.applicationContext())
    private lateinit var sharedPrefsEditor: SharedPreferences.Editor


    fun getRefreshToken(): String? {
        val refreshToken = sharedPref.getString("refreshToken", null)
        return refreshToken
    }


    fun getAccessToken(): String? {
        return sharedPref.getString("accessToken", null)
    }


    fun saveAccessToken(token: String?) {

        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putString("accessToken", token)
        sharedPrefsEditor.apply()
    }

    fun saveRefreshToken(token: String?) {
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putString("refreshToken", token)
        sharedPrefsEditor.apply()
    }

    fun setLoggedIn() {
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putBoolean("LOGGED_IN", true)
        sharedPrefsEditor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean("LOGGED_IN", false)
    }

    fun setOnBoard() {
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putBoolean("ON_BOARD", true)
        sharedPrefsEditor.apply()
    }

    fun finishedOnBoard(): Boolean {
        return sharedPref.getBoolean("ON_BOARD", false)
    }

    fun saveProfile(value: UserInfo) {
        val gson = Gson()
        val res = gson.toJson(value)
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putString("UserProfile", res)
        sharedPrefsEditor.apply()
    }

    fun clearPreferences() {
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.clear()
        sharedPrefsEditor.apply()
    }

    fun getProfile(): UserInfo? {
        val gson = Gson()
        return if (sharedPref.getString("UserProfile", null) != null) {
            gson.fromJson(sharedPref.getString("UserProfile", null), UserInfo::class.java)
        } else {
            null
        }
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
