package com.adrian.snoozeloo.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adrian.snoozeloo.data.model.AlarmData

@Composable
fun AlarmDetailScreen(
    onSaveAlarm: (AlarmData) -> Unit,
    navigateToRingtoneSettings: () -> Unit){
    var hours by remember { mutableStateOf("") }
    var minutes by remember { mutableStateOf("") }
    var alarmName by remember { mutableStateOf("") }

}



fun validateTime(hours: String, minutes: String): Boolean {
    val h = hours.toIntOrNull()
    val m = minutes.toIntOrNull()
    return h in 0..23 && m in 0..59
}