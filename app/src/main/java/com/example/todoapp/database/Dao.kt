package com.example.todoapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.Date

@Dao
interface  Dao {
    @Insert
    fun insert (task: Tasks)

    @Delete
    fun delete (task: Tasks)

    @Update
    fun upData (task: Tasks)

    @Query("Select * from tasks")
    fun showAll ():List<Tasks>

    @Query ("select * from tasks where time= :date ")
    fun showWithDate(date:Long):List<Tasks>
}