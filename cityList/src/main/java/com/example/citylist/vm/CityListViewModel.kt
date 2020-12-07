package com.example.citylist.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.CityWeather
import com.example.core.repository.WeatherRepository

class CityListViewModel(private var repository: WeatherRepository) : ViewModel() {
    var city = MutableLiveData<CityWeather>()
    var cityFindMatches: MutableLiveData<List<CityWeather>> = MutableLiveData()
    var cityList: MutableLiveData<MutableList<CityWeather>> = MutableLiveData(mutableListOf())

    fun loadCity(cityName: String) {
        repository.getWeatherByCity(cityName)
            .subscribe { cityResponse ->
                city.value = cityResponse
            }
    }

    fun findCity(cityName: String) {
        repository.findByCity(cityName)
            .subscribe { findResponse -> cityFindMatches.value = findResponse }
    }
}
