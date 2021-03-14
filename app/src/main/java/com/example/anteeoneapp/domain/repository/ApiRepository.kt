package com.example.anteeoneapp.domain.repository

import com.example.anteeoneapp.data.Coordinates
import com.example.anteeoneapp.data.jsonmodel.WeatherDetailModel
import com.example.anteeoneapp.data.jsonmodel.WeatherListModel

interface ApiRepository {

    suspend fun getWeatherList(coordinates: Coordinates): WeatherListModel

    suspend fun getWeather(query:String): WeatherDetailModel


}