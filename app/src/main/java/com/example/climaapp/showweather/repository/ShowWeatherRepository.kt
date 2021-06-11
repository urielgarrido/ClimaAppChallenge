package com.example.climaapp.showweather.repository

import com.example.climaapp.showweather.model.WeatherCityResponse

interface ShowWeatherRepository {

    suspend fun getWeatherApi(cityName: String, apiKey: String): WeatherCityResponse?
}