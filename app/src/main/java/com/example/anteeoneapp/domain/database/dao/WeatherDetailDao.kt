package com.example.anteeoneapp.domain.database.dao

import androidx.room.*
import com.example.anteeoneapp.data.dto.WeatherDetailDto

@Dao
interface WeatherDetailDao {

    @Query("SELECT * FROM WeatherDetailDto WHERE NAME = :name LIMIT 1")
    fun getByName(name:String):WeatherDetailDto

//    @Query("UPDATE WeatherDetailDto SET name = :name, humidity = :humidity, pressure = :pressure, temperature = :temp, tempMax = :tempMax,tempMin = :tempMin ")
//    fun update(
//        name:String,
//        humidity:Int,
//        pressure:Int,
//        temp:Double,
//        tempMax:Double,
//        tempMin:Double,
//        sunrise:Long,
//        sunset:Long,
//        deg:Int,
//        speed:Double
//    )

    @Delete
    fun delete(weatherDetailDto: WeatherDetailDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(weatherDetailDto: WeatherDetailDto)

    @Query("SELECT EXISTS(SELECT * FROM WeatherDetailDto WHERE name = :name)")
    fun isExist(name : String) : Boolean
}