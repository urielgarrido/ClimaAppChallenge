package com.example.climaapp.showweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.climaapp.showweather.model.WeatherCityResponse
import com.example.climaapp.showweather.repository.ShowWeatherRepository

class ShowWeatherViewModel(
    private val showWeatherRepository: ShowWeatherRepository
): ViewModel() {

    private val _showLoading = MutableLiveData(false)
    val showLoading: LiveData<Boolean> get() = _showLoading

    private fun load(show: Boolean){
        _showLoading.postValue(show)
    }

    suspend fun getWeatherFromApi(cityName: String, apiKey: String): WeatherCityResponse? {
        load(true)
        return showWeatherRepository.getWeatherApi(cityName, apiKey).also {
            load(false)
        }
    }
}