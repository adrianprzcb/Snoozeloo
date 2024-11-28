package com.adrian.snoozeloo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
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
import androidx.compose.material3.Text
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.ui.Alignment
import com.adrian.snoozeloo.data.model.Alarm
import com.adrian.snoozeloo.data.model.AlarmData
import com.adrian.snoozeloo.ui.components.AlarmNameDialog

@Composable
fun AlarmDetailScreen(
    alarm: AlarmData?,
    onSaveAlarm: (AlarmData) -> Unit,
    navigateToRingtoneSettings: () -> Unit){

    val isNewAlarm = alarm == null

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


        // Chips for weekdays selection
        @Composable
        fun DaySelectionChips(
            selectedDays: List<String>,
            onDaySelected: (String) -> Unit
        ) {
            val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(days) { day ->
                    FilterChip(
                        selected = selectedDays.contains(day),
                        onClick = { onDaySelected(day) },
                        label = { Text(day) },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        // Ringtone setting card
        Card(
            onClick = navigateToRingtoneSettings,
            modifier = Modifier.fillMaxWidth().clickable { navigateToRingtoneSettings() }
        ) {
            Text("Ringtone: $ringtone", modifier = Modifier.padding(16.dp))
        }

        // Volume setting slider
        Text("Volume: ${volume.toInt()}%")
        Slider(
            value = volume,
            onValueChange = { volume = it },
            valueRange = 0f..100f,
            steps = 4,
            modifier = Modifier.fillMaxWidth()
        )

        // Vibrate toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Vibrate")
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = vibrate, onCheckedChange = { vibrate = it })
        }

        // Save button
        Button(
            onClick = {
                onSaveAlarm(
                    AlarmData(
                        hours = hours.toInt(),
                        minutes = minutes.toInt(),
                        name = alarmName,
                        days = selectedDays,
                        ringtone = ringtone,
                        volume = volume,
                        vibrate = vibrate
                    )
                )
            },
            enabled = isTimeValid
        ) {
            Text("Save Alarm")
        }
    }

    // Dialog for alarm name input
    if (isNameDialogVisible) {
        AlarmNameDialog(
            initialName = alarmName,
            onDismiss = { isNameDialogVisible = false },
            onSave = {
                alarmName = it
                isNameDialogVisible = false
            }
        )
    }
}


fun validateTime(hours: String, minutes: String): Boolean {
    val h = hours.toIntOrNull()
    val m = minutes.toIntOrNull()
    return h in 0..23 && m in 0..59
}