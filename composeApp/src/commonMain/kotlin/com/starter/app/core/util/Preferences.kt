package com.starter.app.core.util

import com.russhwolf.settings.Settings

class Preferences(private val settingsPref: Settings) {

    companion object {
        const val IS_LOGGED_IN = "IS_LOGGED_IN"
    }


    /**
     * Generic function to save string
     */
    fun saveString(key: String, value: String) {
        settingsPref.putString(key, value)
    }

    /**
     * Generic function to get string
     */
    fun getString(key: String, defaultValue: String = ""): String {
        return settingsPref.getString(key, defaultValue)
    }

    /**
     * Generic function to save boolean
     */
    fun saveBoolean(key: String, value: Boolean) {
        settingsPref.putBoolean(key, value)
    }

    /**
     * Generic function to get boolean
     */
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return settingsPref.getBoolean(key, defaultValue)
    }
}

