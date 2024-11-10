package com.adrian.snoozeloo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time: String,
    val name: String?,
    val repeatDays: List<Int>, // Days of the week (e.g., 1 for Monday)
    val ringtone: String?,
    val vibrate: Boolean,
    val enabled: Boolean
)