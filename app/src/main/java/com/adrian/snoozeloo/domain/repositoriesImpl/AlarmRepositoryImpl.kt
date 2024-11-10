package com.adrian.snoozeloo.domain.repositoriesImpl

import com.adrian.snoozeloo.data.local.AlarmDao
import com.adrian.snoozeloo.domain.model.Alarm
import com.adrian.snoozeloo.domain.repositories.AlarmRepository
import kotlinx.coroutines.flow.Flow

class AlarmRepositoryImpl(private val alarmDao: AlarmDao) : AlarmRepository {
    override fun getAllAlarms(): Flow<List<Alarm>> = alarmDao.getAllAlarms()

    override suspend fun insertAlarm(alarm: Alarm) = alarmDao.insertAlarm(alarm)

    override suspend fun updateAlarm(alarm: Alarm) = alarmDao.updateAlarm(alarm)

    override suspend fun deleteAlarm(alarm: Alarm) = alarmDao.deleteAlarm(alarm)
}