package com.adrian.snoozeloo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adrian.snoozeloo.domain.viewmodel.AlarmViewModel
import com.adrian.snoozeloo.ui.items.AlarmListItem

@Composable
fun AlarmListScreen(viewModel: AlarmViewModel) {
    val alarms = viewModel.alarms.collectAsState()


    LazyColumn {
        items(alarms.value.size) { index ->
            AlarmListItem(alarm = alarms.value[index], onToggle = { updatedAlarm ->
                // Handle toggle action here, e.g., update the alarm in the ViewModel
                viewModel.toggleAlarm(updatedAlarm)
            })
        }
    }

    // Add a FloatingActionButton to create a new alarm
    FloatingActionButton(onClick = { /* Navigate to AlarmDetailScreen */ }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Alarm")
    }

    // Handle the empty state
    if (alarms.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No Alarms")
        }
    }
}
