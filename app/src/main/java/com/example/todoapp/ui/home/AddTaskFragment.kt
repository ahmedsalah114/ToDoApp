package com.example.todoapp.ui.home

import android.app.DatePickerDialog
import java.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.database.DataBase
import com.example.todoapp.database.Tasks
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.ui.clearTime
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddTaskFragment:BottomSheetDialogFragment() {
    lateinit var viewBinding:FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding= FragmentAddTaskBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.addTaskBtn.setOnClickListener {
            addTaskButtonValidation()
        }
        viewBinding.dateContainer.setOnClickListener {
            showDatePicker()
        }

    }
    var calendar = Calendar.getInstance()
    private fun showDatePicker() {
        val dialog=DatePickerDialog(requireContext())
        dialog.setOnDateSetListener { dataPicker, year, month, day ->

            viewBinding.date.setText("$day-${month+1}-$year")

            calendar.set(year,month+1,day)
            Log.e("tag" ,"bind${day/month/year}")
            calendar.clearTime()
        }
        dialog.show()
    }

    private fun addTaskButtonValidation() {
        if (!validation()){
            return
        }
        val task =Tasks(
            tittle = viewBinding.tittle.text.toString(),
            description = viewBinding.description.text.toString(),
            time = calendar.timeInMillis
        )
        DataBase.getInstance(requireContext())
            .dataBaseDao()
            .insert(task)
        onTaskAddedListener?.onTaskAdded()
        dismiss()
    }
    var onTaskAddedListener : OnTaskAddedListener?=null
    fun interface OnTaskAddedListener{
        fun onTaskAdded()
    }
    private fun validation():Boolean{
        var isValid =true
        if (viewBinding.tittle.text.toString().isNullOrBlank()){
            viewBinding.tittleContainer.error="please enter tittle"
            isValid =false
        }else {
            viewBinding.tittleContainer.error=null
        }
        if (viewBinding.description.text.toString().isNullOrBlank()){
            viewBinding.descriptionContainer.error="please enter description"
            isValid =false
        }else {
            viewBinding.descriptionContainer.error=null
        }
        if (viewBinding.date.text.toString().isNullOrBlank()){
            viewBinding.dateContainer.error="please choose date"
            isValid =false
        }else {
            viewBinding.dateContainer.error=null
        }
        return isValid
    }
}