package com.example.anteeoneapp

import android.app.Application
import androidx.room.Room
import com.example.anteeoneapp.data.AppDatabase
import com.example.anteeoneapp.data.dao.TodoDao

class App : Application() {

    lateinit var database: AppDatabase
    lateinit var todoDao: TodoDao

    companion object {

        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-db")
            .allowMainThreadQueries()
            .build()
        todoDao = database.todoDao()
    }
}