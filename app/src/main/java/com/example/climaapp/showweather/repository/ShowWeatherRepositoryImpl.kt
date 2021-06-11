package com.example.climaapp.showweather.repository

import com.example.climaapp.showweather.model.WeatherCityResponse
import com.example.climaapp.showweather.provider.WeatherRetrofit

class ShowWeatherRepositoryImpl: ShowWeatherRepository{

    override suspend fun getWeatherApi(cityName: String, apiKey: String): WeatherCityResponse? {
        val createApi = WeatherRetrofit.createApi
        val response = createApi.getActualWeather(cityName, apiKey, "metric")
        return response.body()
    }
}