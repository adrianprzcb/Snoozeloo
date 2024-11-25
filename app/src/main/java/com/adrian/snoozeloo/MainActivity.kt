package com.adrian.snoozeloo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.adrian.snoozeloo.data.database.AlarmDatabase
import com.adrian.snoozeloo.data.repository.AlarmRepository
import com.adrian.snoozeloo.ui.navigation.AppNavHost
import com.adrian.snoozeloo.ui.theme.SnoozelooTheme

class MainActivity : ComponentActivity() {

    private lateinit var repository: AlarmRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the database and repository
        val database = AlarmDatabase.getInstance(applicationContext)
         repository = AlarmRepository(database.alarmDao())


        setContent {
            SnoozelooTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}

