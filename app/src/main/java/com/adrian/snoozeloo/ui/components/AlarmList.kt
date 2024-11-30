package com.adrian.snoozeloo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adrian.snoozeloo.data.model.Alarm
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun AlarmList (
    alarms: List<Alarm>,
    onToggle: (Alarm) -> Unit
){
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues()
    ){
        items(alarms.size){ index ->
            AlarmItem(
                alarm = alarms[index],
                onToggle = onToggle
            )
        }
    }
}

@Composable
fun AlarmItem(
    alarm: Alarm,
    onToggle: (Alarm) -> Unit
) {

    val currentTime = LocalTime.now()
    val nextOccurrence = calculateNextOccurrence(alarm.time, currentTime)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp) ,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Weight for time row
            ) {
                Text(
                    text = alarm.time,
                    fontSize = 24.sp, // Increased font size
                    modifier = Modifier.padding(start = 8.dp) // Left padding
                )
                Spacer(modifier = Modifier.weight(1f)) // Spacer for alignment
            }
            Row(
                modifier = Modifier.fillMaxWidth() // Full width for next info
            ) {
                Text(
                    text = alarm.alarmName ?: "Untitled Alarm",
                    style = MaterialTheme.typography.bodySmall // Smaller name
                )
                Spacer(modifier = Modifier.weight(1f)) // Spacer for alignment
                Text(
                    text = "NEXT",
                    style = MaterialTheme.typography.bodySmall // Smaller and bold NEXT
                   // color = MaterialTheme.colors.primary // Adjust color based on theme
                )
                Text(
                    text = nextOccurrence,
                    style = MaterialTheme.typography.bodySmall // Smaller next occurrence
                )
                Spacer(modifier = Modifier.weight(1f)) // Spacer for alignment
                Text(
                    text = getDaysForRepeat(alarm), // Get days for repeating alarm
                    style = MaterialTheme.typography.bodySmall // Smaller days text
                )
            }
            Row( // Added a new row for the switch
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp) // Add some top padding
            ) {
                Switch(
                    checked = alarm.isEnabled,
                    onCheckedChange = { onToggle(alarm) }
                )
            }
        }
    }
}

// Function to get comma-separated days for repeating alarm (example)
fun getDaysForRepeat(alarm: Alarm): String {
    // Implement logic to get recurring days based on alarm.repeat
    // Example: return if (alarm.isMonday) "Mon" else ""
    return "Implement logic to get days"
}


fun calculateNextOccurrence(alarmTimeString: String, currentTime: LocalTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val alarmTime = LocalTime.parse(alarmTimeString, formatter) // Parse the string into LocalTime

    val duration = if (alarmTime.isAfter(currentTime)) {
        ChronoUnit.MINUTES.between(currentTime, alarmTime)
    } else {
        ChronoUnit.MINUTES.between(currentTime, alarmTime.plusHours(24))
    }

    val hours = duration / 60
    val minutes = duration % 60
    return "${hours}h ${minutes}min"
}