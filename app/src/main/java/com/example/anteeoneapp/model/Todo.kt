package com.example.anteeoneapp.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(

    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "timestamp")
    var timeStamp: Long
    ) {

}
