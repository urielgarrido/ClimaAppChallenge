package com.example.climaapp.showweather.repository

import com.example.climaapp.showweather.model.WeatherCityForFiveDaysResponse
import com.example.climaapp.showweather.model.WeatherCityResponse

interface ShowWeatherRepository {

    suspend fun getWeatherForToday(cityName: String, apiKey: String): WeatherCityResponse?

    suspend fun getWeatherForFiveDays(cityName: String, apiKey: String): WeatherCityForFiveDaysResponse?
}