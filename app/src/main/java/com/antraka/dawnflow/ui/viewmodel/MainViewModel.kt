package com.antraka.dawnflow.ui.viewmodel

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {
    private val _themeState  = MutableStateFlow<Boolean>(false)
    val themeState = _themeState.asStateFlow()

    fun toggleTheme() {
        _themeState.value = !_themeState.value
    }
}