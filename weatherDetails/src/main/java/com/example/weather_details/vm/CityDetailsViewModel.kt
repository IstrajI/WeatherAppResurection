package com.example.weather_details.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.model.CityWeather
import com.example.weather_details.ui.WeatherDetailsActivity

class CityDetailsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val intentDataStoreAsLiveData =
        savedStateHandle.getLiveData<CityWeather>(WeatherDetailsActivity.EXTRA_KEY)

}