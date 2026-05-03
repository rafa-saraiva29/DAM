package com.example.cooljpweatherapp.ui

data class WeatherUIState (
    val latitude: Float = 38.7071f,
    val longitude: Float = -9.13549f,
    val temperature: Float = 0.0f,
    val windspeed: Float = 0.0f,
    val winddirection: Int = 0,
    val weathercode: Int = 0,
    val seaLevelPressure: Number = 0.0f,
    val time: String = ""
)