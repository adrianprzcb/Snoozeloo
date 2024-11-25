package com.adrian.snoozeloo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "alarmList") {
        composable("alarmList") {
          //  AlarmListScreen(navController)
        }
        composable("alarmDetail") {
         //   AlarmDetailScreen(navController)
        }
    }
}