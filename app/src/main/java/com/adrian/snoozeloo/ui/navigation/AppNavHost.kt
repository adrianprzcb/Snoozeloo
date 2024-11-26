package com.adrian.snoozeloo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
            val alarm = alarmViewModel.getAlarmById(alarmId ?: -1).collectAsState()
            AlarmDetailScreen(
                alarm = alarm,
                onSaveAlarm = { alarmData ->
                    alarmViewModel.saveAlarm(alarmData)
                    navController.popBackStack() // Navigate back to the list
                },
                navigateToRingtoneSettings = {
                    navController.navigate("ringtone_settings")
                }
            )
        }
    }
}