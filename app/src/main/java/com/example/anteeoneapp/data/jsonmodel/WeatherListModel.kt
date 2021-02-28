package com.example.anteeoneapp.data.jsonmodel


import com.google.gson.annotations.SerializedName

data class WeatherListModel(
    @SerializedName("list")
    val list: List<WeatherDetail>
) {
    data class WeatherDetail (
        @SerializedName("main")
        val main: Main,
        @SerializedName("name")
        val name: String,
        @SerializedName("rain")
        val rain: Any?,
        @SerializedName("wind")
        val wind: Wind
    ) {
        data class Main(
            @SerializedName("temp")
            val temp: Double
        )

        data class Wind(
            @SerializedName("speed")
            val speed: Double
        )
    }
}