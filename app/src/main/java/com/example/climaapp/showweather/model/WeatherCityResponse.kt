package com.example.climaapp.showweather.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherCityResponse(
    @SerializedName("dt_txt") var dtText: String?,
    var main: MainRes
) : Parcelable
