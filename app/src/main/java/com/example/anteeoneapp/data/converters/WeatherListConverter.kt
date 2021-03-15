package com.example.anteeoneapp.data.converters

import com.example.anteeoneapp.data.dto.WeatherDto
import com.example.anteeoneapp.data.jsonmodel.WeatherListModel

object weatherListConverter {

    fun convertToDtoList(weatherListModel: WeatherListModel): List<WeatherDto> {
        var result = mutableListOf<WeatherDto>();
        for (from: WeatherListModel.WeatherDetail in weatherListModel.list) {
            result.add(
                WeatherDto(
                    null, from.name, from.main.temp, from.wind.speed, false
                )
            )
        }
        return result
    }

    fun convertToWeatherListModel(weatherDtoList: List<WeatherDto>): WeatherListModel {
        var rl = mutableListOf<WeatherListModel.WeatherDetail>();
        for (i in weatherDtoList) {
            rl.add(
                WeatherListModel.WeatherDetail(
                    WeatherListModel.WeatherDetail.Main(i.temp),
                    i.name,
                    i.rain,
                    WeatherListModel.WeatherDetail.Wind(i.speed)
                )
            )
        }
        return WeatherListModel(rl)
    }


}