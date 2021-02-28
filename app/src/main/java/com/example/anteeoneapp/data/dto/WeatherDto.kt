package com.example.anteeoneapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDto (

    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "temp")
    val temp: Double,

    @ColumnInfo(name = "speed")
    val speed: Double,

    @ColumnInfo(name = "rain")
    val rain: Boolean

)


