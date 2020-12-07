package com.example.core.network.api

import com.example.core.network.responses.CityResponse
import com.example.core.network.responses.FindCityResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherMapApi {
    @GET("weather")
    fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric"
    ): Single<CityResponse>

    @GET("find")
    fun findCityByName(
        @Query("q") cityName: String,
        @Query("type") type: String = "like",
        @Query("units") units: String = "metric"
    ): Observable<FindCityResponse>
}