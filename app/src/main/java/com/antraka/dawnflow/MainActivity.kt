package com.antraka.dawnflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.antraka.dawnflow.ui.states.rememberAppThemeState
import com.antraka.dawnflow.ui.theme.AppTheme
import com.antraka.dawnflow.ui.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeState = rememberAppThemeState()

            AppTheme(darkTheme = themeState.isDarkTheme) {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}