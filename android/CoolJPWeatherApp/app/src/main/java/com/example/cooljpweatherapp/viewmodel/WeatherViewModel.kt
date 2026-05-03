package com.example.cooljpweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.cooljpweatherapp.data.WeatherApiClient
import com.example.cooljpweatherapp.ui.WeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    // UI state
    private val _uiState = MutableStateFlow(WeatherUIState())
    val uiState: StateFlow<WeatherUIState> = _uiState.asStateFlow()

    fun updateLatitude(it: Float) {
        _uiState.value = _uiState.value.copy(latitude = it)
    }

    fun updateLongitude(it: Float) {
        _uiState.value = _uiState.value.copy(longitude = it)
    }

    fun fetchWeather() {
        val lat = _uiState.value.latitude
        val lon = _uiState.value.longitude

        viewModelScope.launch {
            val weatherData = WeatherApiClient.getWeather(lat, lon)

            if (weatherData != null) {
                _uiState.value = _uiState.value.copy(temperature = weatherData.current_weather.temperature)
                _uiState.value = _uiState.value.copy(windspeed = weatherData.current_weather.windspeed)
                _uiState.value = _uiState.value.copy(winddirection = weatherData.current_weather.winddirection)
                _uiState.value = _uiState.value.copy(weathercode = weatherData.current_weather.weathercode)
                _uiState.value = _uiState.value.copy(time = weatherData.current_weather.time)
                _uiState.value = _uiState.value.copy(seaLevelPressure = weatherData.hourly.pressure_msl[_uiState.value.time.substring(11,13).toInt()+1])

            }
        }
    }
}