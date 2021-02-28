package com.example.anteeoneapp.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.anteeoneapp.data.dto.WeatherDto

@Dao
interface WeatherListDao {

    @Query("SELECT * FROM weatherdto")
    fun getAll(): List<WeatherDto>

    @Insert
    fun insert(weatherDto: List<WeatherDto>)

    @Query("delete from weatherdto")
    fun deleteAll()
}