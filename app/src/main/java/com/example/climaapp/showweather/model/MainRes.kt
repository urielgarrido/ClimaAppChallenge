package com.example.climaapp.showweather.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainRes(
    var temp: Double,
    @SerializedName("temp_min") var tempMin: Double,
    @SerializedName("temp_max") var tempMax: Double,
    var pressure: Int,
    var humidity: Int
) : Parcelable
