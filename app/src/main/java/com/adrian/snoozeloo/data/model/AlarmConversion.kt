package com.adrian.snoozeloo.data.model

fun Alarm.toAlarmData(): AlarmData = AlarmData(
    hours = time.split(":")[0].toInt(),
    minutes = time.split(":")[1].toInt(),
    name = alarmName.orEmpty(),
    days = repeatDays,
    ringtone = ringtoneUri,
    volume = 50f, // Default volume if not persisted
    vibrate = vibrate
)

fun AlarmData.toAlarm(): Alarm = Alarm(
    id = 0, // Default ID for new alarms; replace as needed
    time = String.format("%02d:%02d", hours, minutes),
    isEnabled = true,
    repeatDays = days,
    ringtoneUri = ringtone,
    vibrate = vibrate,
    alarmName = name
)
