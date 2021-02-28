package com.example.anteeoneapp.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.anteeoneapp.data.dto.WeatherDetailDto
import com.example.anteeoneapp.data.dto.WeatherDto
import com.example.anteeoneapp.domain.database.dao.WeatherDetailDao
import com.example.anteeoneapp.domain.database.dao.WeatherListDao

@Database(entities = arrayOf(WeatherDto::class,WeatherDetailDto::class),
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    public abstract fun weatherListDao(): WeatherListDao

    public abstract fun weatherDetailDao(): WeatherDetailDao

}