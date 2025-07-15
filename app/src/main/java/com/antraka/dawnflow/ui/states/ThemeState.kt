package com.antraka.dawnflow.ui.states

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.antraka.dawnflow.ui.viewmodel.ThemeViewModel

@Stable
class ThemeState(
    private val context: Context,
    private val themeViewModel: ThemeViewModel,
    initialDarkTheme: Boolean
) {
    var isDarkTheme by mutableStateOf(initialDarkTheme)

    private val preferenceListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == "dark_theme") {
            val sharedPref = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
            isDarkTheme = sharedPref.getBoolean(key, false)
        }
    }

    init {
        context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
            .registerOnSharedPreferenceChangeListener(preferenceListener)
    }

    fun toggleTheme() {
        themeViewModel.toggleTheme()
    }

    fun dispose() {
        context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
            .unregisterOnSharedPreferenceChangeListener(preferenceListener)
    }
}

@Composable
fun rememberAppThemeState(): ThemeState {
    val context = LocalContext.current
    val themeViewModel: ThemeViewModel = viewModel()
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

    val themeState = remember {
        ThemeState(context, themeViewModel, isDarkTheme)
    }

    DisposableEffect(themeState) {
        onDispose {
            themeState.dispose()
        }
    }

    // Update state when ViewModel changes
    LaunchedEffect(isDarkTheme) {
        themeState.isDarkTheme = isDarkTheme
    }

    return themeState
}