package com.example.climaapp.showweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.climaapp.showweather.model.WeatherCityForFiveDaysResponse
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

    private val _weatherDataForToday = MutableLiveData<WeatherCityResponse?>(null)
    val weatherDataForToday get() = _weatherDataForToday

    private suspend fun getWeatherForToday(cityName: String, apiKey: String){
        load(true)
        _weatherDataForToday.postValue(showWeatherRepository.getWeatherForToday(cityName, apiKey)).also {
            load(false)
        }
    }

    private val _weatherDataForFiveDays = MutableLiveData<WeatherCityForFiveDaysResponse?>(null)
    val weatherDataForFiveDays get() = _weatherDataForFiveDays

    private suspend fun getWeatherForFiveDays(cityName: String, apiKey: String) {
        load(true)
        _weatherDataForFiveDays.postValue(showWeatherRepository.getWeatherForFiveDays(cityName, apiKey)).also {
            load(false)
        }
    }

    suspend fun getDataWeather(citySelected: String, apiKey: String) {
        getWeatherForToday(citySelected, apiKey).also {
            getWeatherForFiveDays(citySelected, apiKey)
        }
    }
}