package com.example.anteeoneapp.data.converters

import com.example.anteeoneapp.data.dto.WeatherDetailDto
import com.example.anteeoneapp.data.jsonmodel.WeatherDetailModel

object weatherDetailConverter {

    fun convertToDto(from: WeatherDetailModel) =
        WeatherDetailDto(
            from.name,
            from.main.humidity,
            from.main.pressure,
            from.main.temp,
            from.main.tempMax,
            from.main.tempMin,
            from.sys.sunrise,
            from.sys.sunset,
            from.wind.deg,
            from.wind.speed
        )

    fun convertToModel(from: WeatherDetailDto) =
        WeatherDetailModel(
            WeatherDetailModel.Main(from.humidity,
                from.pressure,
                from.temp,
                from.tempMax,
                from.tempMin),
            from.name,
            WeatherDetailModel.Sys(
                from.sunrise,
                from.sunset
            ),
            WeatherDetailModel.Wind(
                from.deg,
                from.speed
            )
        )
}