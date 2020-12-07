package com.example.weather_details.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.core.model.CityWeather
import com.example.core.repository.WeatherRepository
import com.example.weather_details.R
import com.example.weather_details.vm.CityDetailsViewModel
import com.example.weather_details.vm.MyViewModelFactory
import kotlinx.android.synthetic.main.city_details_activity.*
import javax.inject.Inject

class WeatherDetailsActivity : AppCompatActivity() {
    private val viewModel: CityDetailsViewModel by viewModels {
        MyViewModelFactory(
            this,
            intent.extras
        )
    }

    @Inject
    lateinit var repository: WeatherRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_details_activity)

        viewModel.intentDataStoreAsLiveData.observe(this) {
            if (it == null) return@observe
            cityName.text = it.name
            country.text = it.country
            temperature.text = it.temperature.toString()
            weatherDescription.text = it.weatherDescription
            latitudeDescription.text = it.latitude.toString()
            longitudeDescription.text = it.longitude.toString()
            pressureDescription.text = it.pressure.toString()
            humidityDescription.text = it.humidity.toString()
            windSpeedDescription.text = it.windSpeed.toString()
            windDegreeDescription.text = it.windDegree.toString()
            cloudDescription.text = it.clouds.toString()
        }
    }

    companion object {
        const val EXTRA_KEY = "com.example.weatherapp.CITY_EXTRA"
        fun openWeatherDetails(startingActivity: Activity, city: CityWeather) {
            startingActivity.startActivity(
                Intent(startingActivity, WeatherDetailsActivity::class.java).apply {
                    putExtra(EXTRA_KEY, city)
                })
        }
    }
}