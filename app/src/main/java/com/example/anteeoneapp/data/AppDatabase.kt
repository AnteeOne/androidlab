package com.example.anteeoneapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.anteeoneapp.data.dao.TodoDao
import com.example.anteeoneapp.model.Todo

@Database(entities = arrayOf(Todo::class),
            version = 1,
            exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    public abstract fun todoDao():TodoDao
}