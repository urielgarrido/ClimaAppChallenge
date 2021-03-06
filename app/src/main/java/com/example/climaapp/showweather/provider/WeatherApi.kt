package com.example.climaapp.showweather.provider

import com.example.climaapp.showweather.model.WeatherCityForFiveDaysResponse
import com.example.climaapp.showweather.model.WeatherCityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeatherForToday(
        @Query("q") cityName: String,
        @Query("appid") appId: String,
        @Query("units") units: String,
    ): Response<WeatherCityResponse>

    @GET("data/2.5/forecast")
    suspend fun getWeatherForFiveDays(
        @Query("q") cityName: String,
        @Query("appid") appId: String,
        @Query("units") units: String,
        @Query("cnt") cnt: Int
    ): Response<WeatherCityForFiveDaysResponse>
}