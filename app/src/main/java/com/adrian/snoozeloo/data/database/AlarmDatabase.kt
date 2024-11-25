package com.adrian.snoozeloo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adrian.snoozeloo.data.model.Alarm

@Database(entities = [Alarm::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AlarmDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    companion object {
        @Volatile
        private var INSTANCE: AlarmDatabase? = null
    }

    fun getInstance(context: Context): AlarmDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AlarmDatabase::class.java,
                "alarm_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}

