package com.example.anteeoneapp

import android.app.Application
import androidx.room.Room
import com.example.anteeoneapp.domain.database.AppDatabase
import com.example.anteeoneapp.domain.database.dao.WeatherDetailDao
import com.example.anteeoneapp.domain.database.dao.WeatherListDao
import moxy.MvpFacade

class App : Application() {

    lateinit var database: AppDatabase
    lateinit var weatherListDao: WeatherListDao
    lateinit var weatherDetailDao: WeatherDetailDao

    companion object {

        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "3app-db")
            .allowMainThreadQueries()
            .build()
        weatherListDao = database.weatherListDao()
        weatherDetailDao = database.weatherDetailDao()
        MvpFacade.init()

    }
}