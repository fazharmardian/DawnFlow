package com.antraka.dawnflow.util

import android.app.Activity
import android.content.Context
import androidx.navigation.NavHostController

fun NavHostController.popBackStackOrFinish(context: Context) {
    if (!popBackStack()) {
        if (context is Activity) {
            context.finish()
        }
    }
}