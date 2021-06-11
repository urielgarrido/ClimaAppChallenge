package com.example.climaapp.showweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.climaapp.showweather.repository.ShowWeatherRepository

class ShowWeatherViewModelFactory(
    private val repository: ShowWeatherRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowWeatherViewModel(repository) as T
    }
}