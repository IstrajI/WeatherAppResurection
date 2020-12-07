package com.example.core.network.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CityResponse (
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val id: Long,
    val name: String,
    val cod: Long): Serializable {
    override fun toString(): String {
        return name
    }
}

data class Clouds (
    val all: Long
): Serializable

data class Coord (
    val lon: Double,
    val lat: Double
): Serializable

data class Main (
    val temp: Double,
    val pressure: Long,
    val humidity: Long,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("temp_max")
    val tempMax: Double
): Serializable

data class Sys (
    val type: Long,
    val id: Long,
    val message: Double,
    val country: String,
    val sunrise: Long,
    val sunset: Long
): Serializable

data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
): Serializable

data class Wind (
    val speed: Double,
    val deg: Long
): Serializable