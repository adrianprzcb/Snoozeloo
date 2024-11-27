package com.adrian.snoozeloo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.adrian.snoozeloo.data.model.toAlarm
import com.adrian.snoozeloo.data.model.toAlarmData
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
             onAddAlarm = {navController.navigate("alarm_detail/-1")}
         )
        }
        // Alarm detail screen for adding or editing
        composable(
            route = "alarm_detail/{alarmId}",
            arguments = listOf(
                navArgument("alarmId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val alarmId = backStackEntry.arguments?.getInt("alarmId") ?: -1
            val alarmE by alarmViewModel.getAlarmById(alarmId).collectAsState(initial = null)

            AlarmDetailScreen(
                alarm = alarmE?.toAlarmData(),
                onSaveAlarm = { alarmData ->
                    val alarmToSave = alarmData.toAlarm().copy(id = alarmE?.id ?: 0)
                    alarmViewModel.saveAlarm(alarmToSave)
                    navController.popBackStack()
                },
                navigateToRingtoneSettings = {
                    navController.navigate("ringtone_settings")
                }
            )
        }


    }
}