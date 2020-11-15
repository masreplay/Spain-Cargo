package com.enjaz.hr.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.enjaz.hr.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}