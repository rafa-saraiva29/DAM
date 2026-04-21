package dam_47484.coolweatherapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {
    var day = true
    override fun onCreate(savedInstanceState: Bundle?) {
        //super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT ->
                if (day) setTheme(R.style.Theme_Day)
                else setTheme(R.style.Theme_Night)
            Configuration.ORIENTATION_LANDSCAPE ->
                if (day) setTheme(R.style.Theme_Day_Land)
                else setTheme(R.style.Theme_Night_Land)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lat_default = 38.7167f
        val long_default = -9.1333f

        val lat_elem: TextView = findViewById(R.id.editTextLat)
        val long_elem: TextView = findViewById(R.id.editTextLong)

        lat_elem.text = lat_default.toString()
        long_elem.text = long_default.toString()

        fetchWeatherData(lat_default,long_default).start()

        val button: Button = findViewById(R.id.update_btn)
        button.setOnClickListener{
            val latitude: Float = lat_elem.text.toString().toFloat()
            val longitude: Float = long_elem.text.toString().toFloat()

            fetchWeatherData(latitude,longitude).start()
        }

    }

    private fun WeatherAPI_Call (lat: Float, long: Float) : WeatherData {

        val reqString = buildString {
            append("https://api.open-meteo.com/v1/forecast?")
            append("latitude=${lat}&longitude=${long}&")
            append("current_weather=true&")
            append("current=temperature_2m,is_day,weather_code,wind_speed_10m,wind_direction_10m&")
            append("hourly=pressure_msl&")
            append("timezone=auto&forecast_days=1")
        }
        //https://api.open-meteo.com/v1/forecast?latitude=38.7167&longitude=-9.1333&current=temperature_2m,is_day,weather_code,wind_speed_10m,wind_direction_10m&hourly=pressure_msl&timezone=auto&forecast_days=1
        val str = reqString.toString()
        val url = URL(str);
        url.openStream().use{
            Log.d("MainActivity", "link $str")
            val request = Gson().fromJson(InputStreamReader(it, "UTF-8"), WeatherData::class.java )
            Log.d("MainActivity", "request $request")
            return request
        }
    }

    private fun fetchWeatherData (lat: Float,long: Float) : Thread {
        return Thread {
            val weather = WeatherAPI_Call(lat, long)
            updateUI(weather)
        }
    }

    @SuppressLint("SetTextI18n", "DiscouragedApi", "UseCompatLoadingForDrawables")
    private fun updateUI (request: WeatherData) {
        runOnUiThread {
            val weatherImage: ImageView = findViewById(R.id.weather_symbol)
            val pressure: TextView = findViewById(R.id.sea_press_value)
            val time: TextView = findViewById(R.id.time_value)
            val temperature: TextView = findViewById(R.id.temperature_value)
            val windDir: TextView = findViewById(R.id.wind_dir_value)
            val windSpd: TextView = findViewById(R.id.wind_spd_value)
            val isday: Int = request.current_weather.is_day

            if (isday == 1) {
                day = true
            } else {
                day = false
            }

            val timeVal: String = request.current_weather.time
            val pressureIdx = timeVal.substring(11,13)
            time.text = timeVal
            pressure.text = request.hourly.pressure_msl[pressureIdx.toInt()+1].toString() + " hPa"
            temperature.text = request.current_weather.temperature.toString() + "º"
            windDir.text = request.current_weather.winddirection.toString() + "º"
            windSpd.text = request.current_weather.windspeed.toString() + " km/h"


            val mapt = getWeatherCodeMap();
            val wCode = mapt.get(request.current_weather.weathercode)
            val wImage = when(wCode) {
                WMO_WeatherCode.CLEAR_SKY,
                WMO_WeatherCode.MAINLY_CLEAR,
                WMO_WeatherCode.PARTLY_CLOUDY -> if(day) wCode.image + "day" else wCode.image + "night"
                WMO_WeatherCode.OVERCAST,
                WMO_WeatherCode.FOG,
                WMO_WeatherCode.DEPOSITING_RIME_FOG,
                WMO_WeatherCode.DRIZZLE_LIGHT,
                WMO_WeatherCode.DRIZZLE_MODERATE,
                WMO_WeatherCode.DRIZZLE_DENSE,
                WMO_WeatherCode.FREEZING_DRIZZLE_LIGHT,
                WMO_WeatherCode.FREEZING_DRIZZLE_DENSE,
                WMO_WeatherCode.RAIN_SLIGHT,
                WMO_WeatherCode.RAIN_MODERATE,
                WMO_WeatherCode.RAIN_HEAVY,
                WMO_WeatherCode.FREEZING_RAIN_LIGHT,
                WMO_WeatherCode.FREEZING_RAIN_HEAVY,
                WMO_WeatherCode.SNOW_FALL_SLIGHT,
                WMO_WeatherCode.SNOW_FALL_MODERATE,
                WMO_WeatherCode.SNOW_FALL_HEAVY,
                WMO_WeatherCode.SNOW_GRAINS,
                WMO_WeatherCode.RAIN_SHOWERS_SLIGHT,
                WMO_WeatherCode.RAIN_SHOWERS_MODERATE,
                WMO_WeatherCode.RAIN_SHOWERS_VIOLENT,
                WMO_WeatherCode.SNOW_SHOWERS_SLIGHT,
                WMO_WeatherCode.SNOW_SHOWERS_HEAVY,
                WMO_WeatherCode.THUNDERSTORM_SLIGHT_MODERATE,
                WMO_WeatherCode.THUNDERSTORM_HAIL_SLIGHT,
                WMO_WeatherCode.THUNDERSTORM_HAIL_HEAVY -> wCode.image
                else -> wCode ?. image
            }

            val res = getResources()
            weatherImage.setImageResource(R.drawable.clear_day)
            weatherImage.setImageResource(R.drawable.clear_night)
            weatherImage.setImageResource(R.drawable.cloudy)
            weatherImage.setImageResource(R.drawable.drizzle)
            weatherImage.setImageResource(R.drawable.flurries)
            weatherImage.setImageResource(R.drawable.fog)
            weatherImage.setImageResource(R.drawable.fog_light)
            weatherImage.setImageResource(R.drawable.freezing_drizzle)
            weatherImage.setImageResource(R.drawable.freezing_rain)
            weatherImage.setImageResource(R.drawable.freezing_rain_heavy)
            weatherImage.setImageResource(R.drawable.freezing_rain_light)
            weatherImage.setImageResource(R.drawable.ice_pellets)
            weatherImage.setImageResource(R.drawable.ice_pellets_heavy)
            weatherImage.setImageResource(R.drawable.ice_pellets_light)
            weatherImage.setImageResource(R.drawable.mostly_clear_day)
            weatherImage.setImageResource(R.drawable.mostly_clear_night)
            weatherImage.setImageResource(R.drawable.mostly_cloudy)
            weatherImage.setImageResource(R.drawable.partly_cloudy_day)
            weatherImage.setImageResource(R.drawable.partly_cloudy_night)
            weatherImage.setImageResource(R.drawable.rain)
            weatherImage.setImageResource(R.drawable.rain_heavy)
            weatherImage.setImageResource(R.drawable.rain_light)
            weatherImage.setImageResource(R.drawable.snow)
            weatherImage.setImageResource(R.drawable.snow_heavy)
            weatherImage.setImageResource(R.drawable.snow_light)
            weatherImage.setImageResource(R.drawable.tstorm)

            val resID = res.getIdentifier(wImage, "drawable", packageName);
            val drawable = this.getDrawable(resID);

            weatherImage.setImageDrawable(drawable);
        }
    }
}

