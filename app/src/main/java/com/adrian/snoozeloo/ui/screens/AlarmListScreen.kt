package com.adrian.snoozeloo.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.adrian.snoozeloo.data.model.Alarm
import com.adrian.snoozeloo.ui.components.AlarmList
import com.adrian.snoozeloo.ui.components.EmptyState
import com.adrian.snoozeloo.viewmodel.AlarmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListScreen(
    alarmViewModel: AlarmViewModel,
    onAddAlarm: () -> Unit = {}
){
    val alarms = alarmViewModel.alarms.collectAsState(emptyList()) //Observing Alarms

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = onAddAlarm) {
                Icon(Icons.Default.Add , contentDescription = "Add Alarm")
            }
        }
    ){
        paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            if(alarms.value.isEmpty()){
                //Empty State
                EmptyState()
            }else {
                //List of Alarms
                AlarmList(alarms = alarms.value,
                    onToggle = {alarm ->
                        alarmViewModel.saveAlarm(alarm.copy(isEnabled = !alarm.isEnabled))
                    })
            }

        }
    }



}