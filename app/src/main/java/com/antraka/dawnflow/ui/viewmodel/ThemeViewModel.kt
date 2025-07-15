package com.antraka.dawnflow.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPref = application.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    private val _isDarkTheme = MutableStateFlow(sharedPref.getBoolean("dark_theme", false))
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    private val preferenceListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == "dark_theme") {
            _isDarkTheme.value = sharedPref.getBoolean(key, false)
        }
    }

    init {
        sharedPref.registerOnSharedPreferenceChangeListener(preferenceListener)
    }

    override fun onCleared() {
        sharedPref.unregisterOnSharedPreferenceChangeListener(preferenceListener)
        super.onCleared()
    }

    fun toggleTheme() {
        val newValue = !_isDarkTheme.value
        _isDarkTheme.value = newValue
        sharedPref.edit { putBoolean("dark_theme", newValue) }
    }
}