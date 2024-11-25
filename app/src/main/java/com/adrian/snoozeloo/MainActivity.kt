package com.adrian.snoozeloo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.adrian.snoozeloo.ui.navigation.AppNavHost
import com.adrian.snoozeloo.ui.theme.SnoozelooTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnoozelooTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}

