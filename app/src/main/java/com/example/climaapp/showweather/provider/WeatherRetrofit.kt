package com.example.climaapp.showweather.provider

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofit {

    private const val BASE_API = "https://api.openweathermap.org/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val createApi: WeatherApi = retrofit.create(WeatherApi::class.java)
}