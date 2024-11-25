package com.adrian.snoozeloo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarm(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time : String, // Time in HH:MM format
    val isEnabled: Boolean = true,
    val repeatDays: List<String> = emptyList(), // Days of the week to repeat alarm
    val ringtoneUri: String = "",
    val vibrate: Boolean = true,
    val alarmName: String? = null // Optional name for the alarm

)
