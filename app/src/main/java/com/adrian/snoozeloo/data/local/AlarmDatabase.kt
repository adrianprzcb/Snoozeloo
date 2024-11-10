package com.adrian.snoozeloo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adrian.snoozeloo.domain.model.Alarm

@Database(entities = [Alarm::class], version = 1)
abstract class AlarmDatabase : RoomDatabase() {
    abstract val alarmDao: AlarmDao

    companion object
    {
        @Volatile
        private var INSTANCE: AlarmDatabase? = null

        fun getInstance(context: Context): AlarmDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AlarmDatabase::class.java,
                        "alarm_database"

                    ).build()
                    INSTANCE = instance
                }
                return instance

            }
        }
    }
}