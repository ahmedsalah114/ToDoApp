package com.example.todoapp.ui.home.taps.taskslist

import android.view.View
import android.widget.TextView
import com.example.todoapp.R
import com.kizitonwose.calendar.view.ViewContainer

class DayViewContainer(view:View):ViewContainer(view) {
    val dayOfWeek =view.findViewById<TextView>(R.id.day_of_week)
    val numberOfMonth =view.findViewById<TextView>(R.id.number_of_month)
}