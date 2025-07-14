package com.antraka.dawnflow.util
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Serializable
sealed class Screen(val route: String) {
    @Serializable
    data object Home : Screen("home")

    @Serializable
    data class Details(
        val itemId: String,
        val title: String
    ) : Screen("details/{itemId}/{title}") {
        companion object {
            fun createRoute(itemId: String, title: String) = "details/$itemId/${title.encodeToRoute()}"

            fun fromRoute(route: String?): Details? {
                return try {
                    val parts = route?.split("/")
                    if (parts?.size == 3) {
                        Details(
                            itemId = parts[1],
                            title = parts[2].decodeFromRoute()
                        )
                    } else null
                } catch (e: Exception) {
                    null
                }
            }
        }
    }

    data class Settings(
        val userId: Int,
        val preferences: List<String>
    ) : Screen("settings/{userId}/{preferences}") {
        companion object {
            fun createRoute(userId: Int, preferences: List<String>): String {
                val prefsJson = Json.encodeToString(preferences)
                return "settings/$userId/${URLEncoder.encode(prefsJson, "UTF-8")}"
            }

            fun fromRoute(route: String?): Settings? {
                return try {
                    val parts = route?.split("/")
                    if (parts?.size == 3) {
                        val prefsJson = URLDecoder.decode(parts[2], "UTF-8")
                        Settings(
                            userId = parts[1].toInt(),
                            preferences = Json.decodeFromString(prefsJson)
                        )
                    } else null
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}

// Helper functions for URL encoding/decoding
private fun String.encodeToRoute(): String = java.net.URLEncoder.encode(this, "UTF-8")
private fun String.decodeFromRoute(): String = java.net.URLDecoder.decode(this, "UTF-8")