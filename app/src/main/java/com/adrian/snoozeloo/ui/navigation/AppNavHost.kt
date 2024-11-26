package com.adrian.snoozeloo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.adrian.snoozeloo.ui.screens.AlarmDetailScreen
import com.adrian.snoozeloo.ui.screens.AlarmListScreen
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
         AlarmListScreen(
             alarmViewModel = alarmViewModel,
             onAddAlarm = {navController.navigate("alarm_detail")}
         )
        }
        // Alarm detail screen for adding or editing
        composable(
            route = "alarm_detail",
            arguments = listOf(
                navArgument("alarmId") {
                    type = NavType.StringType
                    nullable = true // Allow null for creating a new alarm
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val alarmId = backStackEntry.arguments?.getString("alarmId")
            AlarmDetailScreen(
                alarm = alarmId?.let { alarmViewModel.getAlarmById(it) },
                onSaveAlarm = { alarm ->
                    alarmViewModel.saveAlarm(alarm)
                    navController.popBackStack() // Navigate back to the list
                },
                navigateToRingtoneSettings = {
                    navController.navigate("ringtone_settings")
                }
            )
        }
    }
}