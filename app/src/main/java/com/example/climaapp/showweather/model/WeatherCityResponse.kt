package com.example.climaapp.showweather.model

import com.google.gson.annotations.SerializedName

data class WeatherCityResponse(
    var main: MainRes
)

data class MainRes(
    var temp: Double,
    @SerializedName("feels_like") var feelsLike: Double,
    @SerializedName("temp_min") var tempMin: Double,
    @SerializedName("temp_max") var tempMax: Double,
    var pressure: Int,
    var humidity: Int
)
