package com.adrian.snoozeloo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.adrian.snoozeloo.data.model.Alarm
import com.adrian.snoozeloo.data.model.AlarmData

@Composable
fun AlarmDetailScreen(
    alarm: Alarm?,
    onSaveAlarm: (AlarmData) -> Unit,
    navigateToRingtoneSettings: () -> Unit){
    var hours by remember { mutableStateOf("") }
    var minutes by remember { mutableStateOf("") }
    var alarmName by remember { mutableStateOf("") }
    var isNameDialogVisible by remember { mutableStateOf(false) }
    var selectedDays by remember { mutableStateOf(listOf<String>()) }
    var ringtone by remember { mutableStateOf("Default Ringtone") }
    var volume by remember { mutableStateOf(50f) }
    var vibrate by remember { mutableStateOf(false) }
    var nextTriggerTime by remember { mutableStateOf("N/A") }


    val isTimeValid = validateTime(hours, minutes)
    val context = LocalContext.current



    Column (
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        //Input fields for hours and minutes
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = hours,
                onValueChange = {if (it.isDigitsOnly()) hours = it},
                label = { Text("Hours") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = minutes,
                onValueChange = {if (it.isDigitsOnly()) minutes = it},
                label = { Text("Minutes") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        // Display next trigger time
        Text("Next alarm will trigger in:  $nextTriggerTime")


        // Button to set alarm name
        Button(onClick = { isNameDialogVisible = true }) {
            Text(text = if (alarmName.isNotEmpty()) alarmName else "Set Alarm Name")
        }

    }



}



fun validateTime(hours: String, minutes: String): Boolean {
    val h = hours.toIntOrNull()
    val m = minutes.toIntOrNull()
    return h in 0..23 && m in 0..59
}