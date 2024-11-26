package com.adrian.snoozeloo.data.model

data class AlarmData(
    val hours: Int,
    val minutes: Int,
    val name: String,
    val days: List<String>,
    val ringtone: String,
    val volume: Float,
    val vibrate: Boolean
)
