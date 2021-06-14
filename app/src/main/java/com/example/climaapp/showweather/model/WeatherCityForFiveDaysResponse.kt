package com.example.climaapp.showweather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherCityForFiveDaysResponse(
    var list: List<WeatherCityResponse>
) : Parcelable
