package com.antraka.dawnflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.antraka.dawnflow.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    userId: Int,
    preferences: List<String>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MainViewModel = viewModel()

    val coroutineScope = rememberCoroutineScope()

    Scaffold { padding ->
        Column(
            modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Settings for user $userId")
            Text("Preferences: ${preferences.joinToString()}")
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.toggleTheme()
                    }
                }
            ) {
                Text("Back")
            }
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}