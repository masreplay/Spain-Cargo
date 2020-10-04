package com.enjaz.hr.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.enjaz.hr.R
import com.enjaz.hr.data.model.token.TokenResult
import com.google.gson.Gson


class PrefsManager  constructor(private var context: Context) {
    private var mGson: Gson = Gson()
    private var sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private lateinit var sharedPrefsEditor: SharedPreferences.Editor
    
    private fun getString(id: Int): String {
        return context.getString(id)
    }
    
    fun getTokens(): TokenResult? {
        val data = sharedPref.getString(context.getString(R.string.tokens_key_prefrences), null)
        if (data != null) {
            val gson = Gson()
            return gson.fromJson(data, TokenResult::class.java)
        }
        return null
    }
    fun saveTokens(user: TokenResult?) {
        val gson = Gson()
        val json = gson.toJson(user)
        sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putString(getString(R.string.tokens_key_prefrences), json)
        sharedPrefsEditor.apply()
    }

    companion object {

        var instance: PrefsManager? = null
            private set

        fun init(context: Context) {
            if (instance == null) {
                instance = PrefsManager(context)
            }
        }
    }
}
