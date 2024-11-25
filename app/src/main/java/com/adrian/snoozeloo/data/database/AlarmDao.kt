package com.adrian.snoozeloo.data.database

import androidx.room.Dao
import androidx.room.Query
import com.adrian.snoozeloo.data.model.Alarm
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarms")
    fun getAllAlarms(): Flow<List<Alarm>>
}