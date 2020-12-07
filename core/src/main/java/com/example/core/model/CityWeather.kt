package com.example.core.model
import java.io.Serializable

class CityWeather (
    val name: String,
    val longitude: Double,
    val latitude: Double,
    val clouds: Long,
    val temperature: Double,
    val pressure: Long,
    val humidity: Long,
    val country: String,
    val windSpeed: Double,
    val windDegree: Long,
    val weatherDescription: String) : Serializable