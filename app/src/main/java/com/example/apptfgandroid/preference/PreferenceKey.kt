package com.example.apptfgandroid.preference

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKey {
    val NAME = stringPreferencesKey("name")
    val DARKMODE = booleanPreferencesKey("darkMode")
}