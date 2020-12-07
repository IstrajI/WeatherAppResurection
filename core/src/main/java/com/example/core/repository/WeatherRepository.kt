package com.example.core.repository

import com.example.core.model.CityWeather
import com.example.core.network.api.OpenWeatherMapApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepository @Inject constructor(val api: OpenWeatherMapApi) {
    fun findByCity(cityName: String): Single<List<CityWeather>> {
        //here we could also querry local db if we had it
        return api.findCityByName(cityName)
            .flatMap { Observable.fromIterable(it.list) }
            .map { response ->
                CityWeather(
                    name = response.name,
                    longitude = response.coord.lon,
                    latitude = response.coord.lat,
                    clouds = response.clouds.all,
                    temperature = response.main.temp,
                    pressure = response.main.pressure,
                    humidity = response.main.humidity,
                    windDegree = response.wind.deg,
                    windSpeed = response.wind.speed,
                    country = response.sys.country,
                    weatherDescription = response.weather[0].description
                )
            }.toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getWeatherByCity(cityName: String): Single<CityWeather> {
        //here we could also querry local db if we had it
        return api.getWeatherByCityName(cityName)
            .map { response ->
                CityWeather(
                    name = response.name,
                    longitude = response.coord.lon,
                    latitude = response.coord.lat,
                    clouds = response.clouds.all,
                    temperature = response.main.temp,
                    pressure = response.main.pressure,
                    humidity = response.main.humidity,
                    country = response.sys.country,
                    weatherDescription = response.weather[0].description,
                    windSpeed = response.wind.speed,
                    windDegree = response.wind.deg
                )
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}