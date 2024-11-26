package com.adrian.snoozeloo.ui.screens

import androidx.compose.runtime.Composable
import com.adrian.snoozeloo.data.model.AlarmData

@Composable
fun AlarmDetailScreen(
    onSaveAlarm: (AlarmData) -> Unit,
    navigateToRingtoneSettings: () -> Unit){

}



fun validateTime(hours: String, minutes: String): Boolean {
    val h = hours.toIntOrNull()
    val m = minutes.toIntOrNull()
    return h in 0..23 && m in 0..59
}