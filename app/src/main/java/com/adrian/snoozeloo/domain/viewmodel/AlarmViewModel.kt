package com.adrian.snoozeloo.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.snoozeloo.domain.model.Alarm
import com.adrian.snoozeloo.domain.repositories.AlarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class AlarmViewModel(private val repository: AlarmRepository) : ViewModel() {
    val alarms = repository.getAllAlarms().flowOn(Dispatchers.IO).stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun toggleAlarm(alarm: Alarm) {
        viewModelScope.launch {
            repository.updateAlarm(alarm.copy(enabled = !alarm.enabled))
        }
    }

    // ... (other actions like adding, deleting, and editing alarms)
}