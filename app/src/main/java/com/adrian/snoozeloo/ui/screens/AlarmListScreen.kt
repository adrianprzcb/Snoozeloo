package com.adrian.snoozeloo.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.adrian.snoozeloo.data.model.Alarm
import com.adrian.snoozeloo.viewmodel.AlarmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListScreen(
    alarmViewModel: AlarmViewModel,
    onAddAlarm: () -> Unit = {}
){
    val alarms = alarmViewModel.alarms.collectAsState(emptyList()) //Observing Alarms
}