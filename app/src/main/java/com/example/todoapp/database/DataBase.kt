package com.example.todoapp.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Tasks::class], version  = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun dataBaseDao():Dao
    companion object{
        private var instance :DataBase?=null
        fun getInstance (context :Context):DataBase{
            if (instance==null){
                //initialize
                instance =Room.databaseBuilder(context,DataBase::class.java,"Todo DataBase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }

    }
}