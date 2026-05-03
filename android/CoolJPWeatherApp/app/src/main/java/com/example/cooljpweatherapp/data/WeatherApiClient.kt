package com.example.cooljpweatherapp.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object WeatherApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getWeather(lat: Float, lon: Float): WeatherData? {
        val reqString = buildString {
            append("https://api.open-meteo.com/v1/forecast?")
            append("latitude=${lat}&longitude=${lon}&")
            append("current_weather=true&")
            append("current=temperature_2m,is_day,weather_code,wind_speed_10m,wind_direction_10m&")
            append("hourly=pressure_msl&")
            append("timezone=auto&forecast_days=1")
        }
        Log.d("Main Activity", "Getting URL: $reqString")
        return try {
            client.get(reqString).body() // Parses JSON into WeatherData
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}