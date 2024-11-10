package com.adrian.snoozeloo.ui.items

import androidx.compose.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adrian.snoozeloo.domain.model.Alarm

@Composable
fun AlarmListItem(alarm: Alarm, onToggle: (Alarm) -> Unit) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = alarm.time)
                Text(text = alarm.name ?: "Unnamed Alarm")
                // ... other details
            }
            Switch(
                checked = alarm.enabled,
                onCheckedChange = { onToggle(alarm.copy(enabled = it)) }
            )
        }
    }
}