package com.antraka.dawnflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onNavigateToDetails: (String, String) -> Unit,
    onNavigateToSettings: (Int, List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { padding ->
        Column(
            modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onNavigateToDetails("123", "Sample Item") }
            ) {
                Text("Go to Details")
            }

            Button(onClick = {
                onNavigateToSettings(1, listOf("dark_mode", "notifications"))
            }) {
                Text("Go to Settings")
            }
        }
    }
}