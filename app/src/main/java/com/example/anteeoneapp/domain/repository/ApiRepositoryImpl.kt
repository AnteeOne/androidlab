package com.example.anteeoneapp.domain.repository

import com.example.anteeoneapp.data.Coordinates
import com.example.anteeoneapp.data.jsonmodel.WeatherDetailModel
import com.example.anteeoneapp.data.jsonmodel.WeatherListModel
import com.example.anteeoneapp.domain.network.ApiFactory
import com.example.anteeoneapp.domain.repository.interfaces.ApiRepository

object ApiRepositoryImpl:
    ApiRepository {

    private val api = ApiFactory.weatherApi

    override suspend fun getWeatherList(coordinates:Coordinates): WeatherListModel =
        api.getWeatherList(coordinates.latitude, coordinates.longitude, 50)

    override suspend fun getWeather(query: String): WeatherDetailModel =
        api.getWeather(query)
}