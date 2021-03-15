package com.example.anteeoneapp.domain.network

import com.example.anteeoneapp.data.jsonmodel.WeatherDetailModel
import com.example.anteeoneapp.data.jsonmodel.WeatherListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("find?units=metric&lang=ru")
    suspend fun getWeatherList(
        @Query("lat") latitude:Double,
        @Query("lon") longitude:Double,
        @Query("cnt") count:Int
    ): WeatherListModel

    @GET("weather?units=metric")
    suspend fun getWeather(
        @Query("q") cityName: String
    ) : WeatherDetailModel
}