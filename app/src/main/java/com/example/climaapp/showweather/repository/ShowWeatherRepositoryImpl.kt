package com.example.climaapp.showweather.repository

import com.example.climaapp.showweather.model.WeatherCityForFiveDaysResponse
import com.example.climaapp.showweather.model.WeatherCityResponse
import com.example.climaapp.showweather.provider.WeatherRetrofit

class ShowWeatherRepositoryImpl: ShowWeatherRepository{

    override suspend fun getWeatherForToday(
        cityName: String,
        apiKey: String
    ): WeatherCityResponse? {
        val createApi = WeatherRetrofit.createApi
        val response = createApi.getWeatherForToday(cityName, apiKey, "metric")
        return response.body()
    }

    override suspend fun getWeatherForFiveDays(cityName: String, apiKey: String): WeatherCityForFiveDaysResponse? {
        val createApi = WeatherRetrofit.createApi
        val response = createApi.getWeatherForFiveDays(cityName, apiKey, "metric", 40)
        return response.body()
    }
}