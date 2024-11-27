package com.adrian.snoozeloo.data.repository

import com.adrian.snoozeloo.data.database.AlarmDao
import com.adrian.snoozeloo.data.model.Alarm
import kotlinx.coroutines.flow.Flow

class AlarmRepository(private val alarmDao: AlarmDao) {

    val allAlarms: Flow<List<Alarm>> = alarmDao.getAllAlarms();

    suspend fun getAlarmById(id: Int): Flow<Alarm?> {
        return alarmDao.getAlarmById(id)
    }

    suspend fun insertAlarm(alarm: Alarm) {
        alarmDao.insertAlarm(alarm)
    }

    suspend fun deleteAlarm(alarm: Alarm) {
        alarmDao.deleteAlarm(alarm)
    }

    suspend fun deleteAllAlarms() {
        alarmDao.deleteAllAlarms()
    }
}