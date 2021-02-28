package com.example.anteeoneapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDetailDto(

    @PrimaryKey
    var name: String,
    var humidity: Int,
    var pressure: Int,
    var temp: Double,
    var tempMax: Double,
    var tempMin: Double,
    var sunrise: Long,
    var sunset: Long,
    var deg: Int,
    var speed: Double

)



