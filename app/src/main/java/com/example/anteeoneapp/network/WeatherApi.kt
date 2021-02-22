package com.example.anteeoneapp.network

import com.example.anteeoneapp.data.WeatherDetailModel
import com.example.anteeoneapp.data.WeatherListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("find?units=metric&lang=ru")
    suspend fun getWeatherList(
        @Query("lat") latitude:Double,
        @Query("lon") longitude:Double,
        @Query("cnt") count:Int
    ):WeatherListModel

    @GET("weather?units=metric")
    suspend fun getWeather(
        @Query("q") cityName: String
    ) : WeatherDetailModel
}