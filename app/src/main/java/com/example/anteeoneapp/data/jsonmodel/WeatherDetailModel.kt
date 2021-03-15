package com.example.anteeoneapp.data.jsonmodel

import com.google.gson.annotations.SerializedName

data class WeatherDetailModel(
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("wind")
    val wind: Wind
) {
    data class Main(
        @SerializedName("humidity")
        val humidity: Int,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("temp")
        val temp: Double,
        @SerializedName("temp_max")
        val tempMax: Double,
        @SerializedName("temp_min")
        val tempMin: Double
    )

    data class Sys(
        @SerializedName("sunrise")
        val sunrise: Long,
        @SerializedName("sunset")
        val sunset: Long
    )

    data class Wind(
        @SerializedName("deg")
        val deg: Int,
        @SerializedName("speed")
        val speed: Double
    )
}