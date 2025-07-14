package com.antraka.dawnflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.antraka.dawnflow.ui.theme.AppTheme
import com.antraka.dawnflow.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()

            val themeState by viewModel.themeState.collectAsState()

            AppTheme(darkTheme = themeState) {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}