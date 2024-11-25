package com.adrian.snoozeloo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.adrian.snoozeloo.viewmodel.AlarmViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    alarmViewModel: AlarmViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "alarm_list"
    ) {
        composable("alarm_list") {
       //     AlarmListScreen(alarmViewModel = alarmViewModel)
        }
        // Add other screens here...
    }
}