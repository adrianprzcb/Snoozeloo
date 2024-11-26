package com.adrian.snoozeloo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.adrian.snoozeloo.data.model.Alarm

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
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp , vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = alarm.alarmName ?: "Untitled Alarm",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Next: ${alarm.nextOccurrence}", //Format this dynamically
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Switch(
                checked = alarm.isEnabled,
                onCheckedChange = {onToggle(alarm)}
            )
        }

    }
}