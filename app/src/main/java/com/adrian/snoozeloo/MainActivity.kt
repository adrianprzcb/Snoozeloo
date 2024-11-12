package com.adrian.snoozeloo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.adrian.snoozeloo.domain.viewmodel.AlarmViewModel
import com.adrian.snoozeloo.ui.AlarmListScreen
import com.adrian.snoozeloo.ui.theme.SnoozelooTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<AlarmViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SnoozelooTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "alarm_list_screen") {
                    composable("alarm_list_screen")
                    {
                        AlarmListScreen(viewModel = viewModel)
                    }
                    // Add other screens here as needed
                }
            }
        }
    }

}