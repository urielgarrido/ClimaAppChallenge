package com.example.climaapp.showweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climaapp.showweather.model.WeatherCityForFiveDaysResponse
import com.example.climaapp.showweather.model.WeatherCityResponse
import com.example.climaapp.showweather.repository.ShowWeatherRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ShowWeatherViewModel(
    private val showWeatherRepository: ShowWeatherRepository
) : ViewModel() {

    private val _showLoading = MutableLiveData(false)
    val showLoading: LiveData<Boolean> get() = _showLoading

    private fun load(show: Boolean) {
        _showLoading.postValue(show)
    }

    private val _weatherDataForToday = MutableLiveData<WeatherCityResponse?>(null)
    val weatherDataForToday get() = _weatherDataForToday

    private suspend fun getWeatherForToday(cityName: String, apiKey: String) {
        load(true)
        _weatherDataForToday.postValue(showWeatherRepository.getWeatherForToday(cityName, apiKey))
            .also {
                load(false)
            }
    }

    private val _weatherDataForFiveDays = MutableLiveData<WeatherCityForFiveDaysResponse?>(null)
    val weatherDataForFiveDays get() = _weatherDataForFiveDays

    private suspend fun getWeatherForFiveDays(cityName: String, apiKey: String) {
        load(true)
        _weatherDataForFiveDays.postValue(
            showWeatherRepository.getWeatherForFiveDays(
                cityName,
                apiKey
            )
        ).also {
            load(false)
        }
    }

    fun getDataWeather(citySelected: String, apiKey: String) {
        viewModelScope.launch {
            getWeatherForToday(citySelected, apiKey).also {
                getWeatherForFiveDays(citySelected, apiKey)
            }
        }
    }

    fun getActualHours(): String {
        val actualDate = Date()
        val formatDateToHours = SimpleDateFormat("HH", Locale.getDefault())
        val actualHoursString = formatDateToHours.format(actualDate)

        return when (actualHoursString.toInt() % 3) {
            0 -> {
                actualHoursString
            }
            1 -> {
                val beforeAvailableHourToShowData = actualHoursString.toInt() - 1
                beforeAvailableHourToShowData.toString()
            }
            2 -> {
                val nextAvailableHourToShowData = actualHoursString.toInt() + 1
                nextAvailableHourToShowData.toString()
            }
            else -> "00"
        }
    }
}