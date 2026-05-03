package com.example.cooljpweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.cooljpweatherapp.ui.theme.CoolJPWeatherAppTheme
import androidx.compose.material3.Surface
import com.example.cooljpweatherapp.ui.WeatherUI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoolJPWeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    WeatherUI()
                }
            }
        }
    }
}
