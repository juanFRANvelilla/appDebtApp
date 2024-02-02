package com.example.apptfgandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.apptfgandroid.navigation.AppNavigation
import com.example.apptfgandroid.ui.theme.AppTfgAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTfgAndroidTheme(
                darkTheme = false
            ) {
                AppNavigation()
            }
        }
    }
}
