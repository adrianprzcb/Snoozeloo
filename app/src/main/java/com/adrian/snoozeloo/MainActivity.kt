package com.adrian.snoozeloo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.adrian.snoozeloo.data.database.AlarmDatabase
import com.adrian.snoozeloo.data.repository.AlarmRepository
import com.adrian.snoozeloo.ui.navigation.AppNavHost
import com.adrian.snoozeloo.ui.theme.SnoozelooTheme
import com.adrian.snoozeloo.viewmodel.AlarmViewModel
import com.adrian.snoozeloo.viewmodel.AlarmViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: AlarmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the database and repository
        val database = AlarmDatabase.getInstance(applicationContext)
        val repository = AlarmRepository(database.alarmDao())

        // Initialize ViewModel
        val factory = AlarmViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AlarmViewModel::class.java)

        setContent {
            SnoozelooTheme {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                    alarmViewModel = viewModel // Pass ViewModel to navigation
                )
            }
        }
    }
}
