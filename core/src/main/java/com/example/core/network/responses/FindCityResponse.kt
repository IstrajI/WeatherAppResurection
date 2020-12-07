package com.example.core.network.responses

data class FindCityResponse (
    val message: String,
    val cod: String,
    val count: Long,
    val list: List<CityResponse>
)