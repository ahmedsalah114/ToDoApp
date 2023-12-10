package com.example.todoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity
data class Tasks (
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    var id : Int ?=null,

    @ColumnInfo
    var tittle:String?=null ,

    @ColumnInfo
    var description:String?=null,


    @ColumnInfo
    var time :Long?=null,

    @ColumnInfo
    var isDone:Boolean=false
        )