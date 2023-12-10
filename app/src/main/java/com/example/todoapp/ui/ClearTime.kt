package com.example.todoapp.ui

import java.util.Calendar

fun Calendar.clearTime(){
    set(android.icu.util.Calendar.HOUR_OF_DAY,0)
    set(android.icu.util.Calendar.MINUTE,0)
    set(android.icu.util.Calendar.SECOND,0)
    set(android.icu.util.Calendar.MILLISECOND,0)
}