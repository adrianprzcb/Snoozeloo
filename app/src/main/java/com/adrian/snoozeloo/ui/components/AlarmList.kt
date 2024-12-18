package com.adrian.snoozeloo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

    val currentTime = LocalTime.now() // Get the current time
    val nextOccurrence = calculateNextOccurrence(alarm.time, currentTime)
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp , vertical = 8.dp) ,
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Column (
                modifier = Modifier.weight(1f)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = alarm.time,
                        fontSize = 40.sp
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight().height(40.dp),
                        verticalArrangement = Arrangement.Bottom

                    ) {
                        Text(
                            text = alarm.alarmName ?: "Untitled Alarm",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                }

                Row(modifier = Modifier.fillMaxWidth()) {

                  //TODO  Text(text = getTextRepeatDays())
                    Text(
                        text = "Texto de Prueba | ",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Next: $nextOccurrence", //Format this dynamically
                        style = MaterialTheme.typography.bodyMedium
                    )
                }


            }
            Switch(
                checked = alarm.isEnabled,
                onCheckedChange = {onToggle(alarm)}
            )
        }

    }
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