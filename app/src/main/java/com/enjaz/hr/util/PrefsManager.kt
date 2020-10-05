package com.enjaz.hr.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.enjaz.hr.R
import com.enjaz.hr.data.model.token.TokenResult
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PrefsManager @Inject constructor(
    private var sharedPref: SharedPreferences,
    @ApplicationContext var context: Context
) {


    private fun getString(id: Int): String {
        Log.d("Abdalla19977", "getString")

        return context.getString(id)
    }

    fun getAccessToken(): TokenResult? {
        Log.d("Abdalla19977", "getAccessToken")

        val data = sharedPref.getString(getString(R.string.tokens_key_prefrences), null)
        if (data != null) {
            val gson = Gson()
            return gson.fromJson(data, TokenResult::class.java)
        }
        return null
    }

    fun saveTokens(user: TokenResult?) {
        val gson = Gson()
        val json = gson.toJson(user)
        var sharedPrefsEditor = sharedPref.edit()
        sharedPrefsEditor.putString(getString(R.string.tokens_key_prefrences), json)
        sharedPrefsEditor.apply()
    }

}
