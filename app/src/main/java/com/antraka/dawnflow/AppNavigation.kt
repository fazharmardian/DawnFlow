package com.antraka.dawnflow

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.antraka.dawnflow.ui.screens.DetailsScreen
import com.antraka.dawnflow.ui.screens.HomeScreen
import com.antraka.dawnflow.ui.screens.SettingsScreen
import com.antraka.dawnflow.util.Screen
import com.antraka.dawnflow.util.popBackStackOrFinish
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation(navController: NavHostController) {
    var isNavigating by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToDetails = { itemId, title ->
                    if (!isNavigating) {
                        isNavigating = true
                        coroutineScope.launch {
                            navController.navigate(Screen.Details.createRoute(itemId, title))
                            delay(300)
                            isNavigating = false
                        }
                    }
                },
                onNavigateToSettings = { userId, preferences ->
                    if (!isNavigating) {
                        isNavigating = true
                        coroutineScope.launch {
                            navController.navigate(Screen.Settings.createRoute(userId, preferences))
                            delay(300)
                            isNavigating = false
                        }
                    }
                }
            )
        }

        composable(
            route = "details/{itemId}/{title}",
            arguments = listOf(
                navArgument("itemId") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) { entry ->
            val itemId = entry.arguments?.getString("itemId") ?: ""
            val title = entry.arguments?.getString("title") ?: ""
            DetailsScreen(
                itemId = itemId,
                title = title,
                onBack = {
                    if (!isNavigating) {
                        isNavigating = true
                        coroutineScope.launch {
                            navController.popBackStackOrFinish(context)
                            delay(300)
                            isNavigating = false
                        }
                    }
                }
            )
        }

        composable(
            route = "settings/{userId}/{preferences}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("preferences") { type = NavType.StringType }
            )
        ) { entry ->
            val userId = entry.arguments?.getInt("userId") ?: 0
            val preferencesJson = entry.arguments?.getString("preferences") ?: ""

            val preferences = try {
                Json.decodeFromString<List<String>>(preferencesJson)
            } catch (e: Exception) {
                emptyList<String>()
            }

            SettingsScreen(
                userId = userId,
                preferences = preferences,
                onBack = {
                    if (!isNavigating) {
                        isNavigating = true
                        coroutineScope.launch {
                            navController.popBackStackOrFinish(context)
                            delay(300)
                            isNavigating = false
                        }
                    }
                }
            )
        }
    }
}
