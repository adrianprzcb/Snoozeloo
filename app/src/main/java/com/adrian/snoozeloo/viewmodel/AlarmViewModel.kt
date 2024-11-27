package com.adrian.snoozeloo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.snoozeloo.data.model.Alarm
import com.adrian.snoozeloo.data.repository.AlarmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AlarmViewModel (private val repository: AlarmRepository): ViewModel(){

    //StateFlow for alarms list,making it observable
    val alarms: StateFlow<List<Alarm>> = repository.allAlarms
        .map { alarms -> alarms.sortedBy { it.time } } //Optional: Sort alarms by time
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    //Add or update an alarm
    fun saveAlarm(alarm: Alarm) {
        viewModelScope.launch {
            if (alarm.id == 0) {
                repository.insertAlarm(alarm)
            } else {
              //  repository.updateAlarm(alarm)
            }
        }
    }


    //Delete a specific alarm
    fun deleteAlarm(alarm: Alarm){
        viewModelScope.launch {
            repository.deleteAlarm(alarm)
        }
    }


    //Clear all alarms
    fun clearAllAlarms(){
        viewModelScope.launch {
            repository.deleteAllAlarms()
        }
    }


    //Get a specific alarm by ID
     fun getAlarmById(alarmId: Int): Flow<Alarm?> {
        return if (alarmId == -1) {
            flowOf(null) // New alarm case
        } else {
            repository.getAlarmById(alarmId) // Fetch from database
        }
    }


}