package com.antraka.dawnflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.antraka.dawnflow.ui.states.rememberAppThemeState
import com.antraka.dawnflow.ui.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    userId: Int,
    preferences: List<String>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeState = rememberAppThemeState()

    Scaffold { padding ->
        Column(
            modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { themeState.toggleTheme() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(if (themeState.isDarkTheme) "Light Mode" else "Dark Mode")
            }

            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}