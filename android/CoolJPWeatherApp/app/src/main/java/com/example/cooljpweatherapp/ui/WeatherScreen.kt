package com.example.cooljpweatherapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.example.cooljpweatherapp.data.WMO_WeatherCode
import com.example.cooljpweatherapp.data.getWeatherCodeMap
import com.example.cooljpweatherapp.viewmodel.WeatherViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.cooljpweatherapp.ui.theme.CoolJPWeatherAppTheme


@Composable
fun WeatherUI(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherUIState by weatherViewModel.uiState.collectAsState()

    weatherViewModel.fetchWeather()

    val latitude = weatherUIState.latitude
    val longitude = weatherUIState.longitude
    val temperature = weatherUIState.temperature
    val windSpeed = weatherUIState.windspeed
    val windDirection = weatherUIState.winddirection
    val weathercode = weatherUIState.weathercode
    val seaLevelPressure = weatherUIState.seaLevelPressure
    val time = weatherUIState.time

    val configuration = LocalConfiguration.current
    val day = true

    val weatherCodeMap = getWeatherCodeMap()
    val wCode = weatherCodeMap[weathercode]
    val wImage = when (wCode) {
        WMO_WeatherCode.CLEAR_SKY,
        WMO_WeatherCode.MAINLY_CLEAR,
        WMO_WeatherCode.PARTLY_CLOUDY -> if (day) wCode?.image + "day" else wCode?.image + "night"
        else -> wCode?.image
    }

    val context = LocalContext.current
    val wIcon = context.resources.getIdentifier(wImage, "drawable", context.packageName)

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeWeatherUI(
            wIcon = wIcon,
            latitude = latitude,
            longitude = longitude,
            temperature = temperature,
            windSpeed = windSpeed,
            windDirection = windDirection,
            weathercode = weathercode,
            seaLevelPressure = seaLevelPressure,
            time = time,
            onLatitudeChange = { newValue ->
                newValue.toFloatOrNull()?.let { weatherViewModel.updateLatitude(it) }
            },
            onLongitudeChange = { newValue ->
                newValue.toFloatOrNull()?.let { weatherViewModel.updateLongitude(it) }
            },
            onUpdateButtonClick = { weatherViewModel.fetchWeather() }
        )
    } else {
        PortraitWeatherUI(
            wIcon = wIcon,
            latitude = latitude,
            longitude = longitude,
            temperature = temperature,
            windSpeed = windSpeed,
            windDirection = windDirection,
            weathercode = weathercode,
            seaLevelPressure = seaLevelPressure,
            time = time,
            onLatitudeChange = { newValue ->
                newValue.toFloatOrNull()?.let { weatherViewModel.updateLatitude(it) }
            },
            onLongitudeChange = { newValue ->
                newValue.toFloatOrNull()?.let { weatherViewModel.updateLongitude(it) }
            },
            onUpdateButtonClick = { weatherViewModel.fetchWeather() }
        )
    }
}



@Composable
fun PortraitWeatherUI(
    wIcon: Int,
    latitude: Float,
    longitude: Float,
    temperature: Float,
    windSpeed: Float,
    windDirection: Int,
    weathercode: Int,
    seaLevelPressure: Number,
    time: String,
    onLatitudeChange: (String) -> Unit,
    onLongitudeChange: (String) -> Unit,
    onUpdateButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top: Icon and Temperature
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = wIcon),
                contentDescription = null,
                modifier = Modifier.size(96.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "${temperature}º",
                style = typography.displayLarge,
                color = colorScheme.primary
            )
        }

        // Latitude and Longitude Fields
        OutlinedTextField(
            value = latitude.toString(),
            onValueChange = onLatitudeChange,
            label = { Text("Latitude") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = longitude.toString(),
            onValueChange = onLongitudeChange,
            label = { Text("Longitude") },
            modifier = Modifier.fillMaxWidth()
        )

        // Card with weather info
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Time: $time", style = typography.bodyLarge)
                Text("Wind Speed: $windSpeed km/h", style = typography.bodyLarge)
                Text("Wind Direction: $windDirection", style = typography.bodyLarge)
                Text("Sea Level Pressure: $seaLevelPressure", style = typography.bodyLarge)
            }
        }

        // Update button
        Button(
            onClick = onUpdateButtonClick,
            shape = shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("UPDATE")
        }
    }
}

@Composable
fun LandscapeWeatherUI(
    wIcon: Int,
    latitude: Float,
    longitude: Float,
    temperature: Float,
    windSpeed: Float,
    windDirection: Int,
    weathercode: Int,
    seaLevelPressure: Number,
    time: String,
    onLatitudeChange: (String) -> Unit,
    onLongitudeChange: (String) -> Unit,
    onUpdateButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .safeDrawingPadding(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = wIcon),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "${temperature}º",
                style = typography.displayLarge,
                color = colorScheme.primary
            )
        }

        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = latitude.toString(),
                onValueChange = onLatitudeChange,
                label = { Text("Latitude") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = longitude.toString(),
                onValueChange = onLongitudeChange,
                label = { Text("Longitude") },
                modifier = Modifier.fillMaxWidth()
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Time: $time", style = typography.bodyLarge)
                    Text("Wind Speed: $windSpeed km/h", style = typography.bodyLarge)
                    Text("Wind Direction: $windDirection", style = typography.bodyLarge)
                    Text("Sea Level Pressure: $seaLevelPressure", style = typography.bodyLarge)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onUpdateButtonClick,
                shape = shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("UPDATE")
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    CoolJPWeatherAppTheme {
        WeatherUI()
    }
}