package com.example.todoapp.ui.home.taps.taskslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.database.Tasks

import com.example.todoapp.databinding.TaskItemBinding

class TasksListAdapter(var tasks:List<Tasks> ?=null):RecyclerView.Adapter<TasksListAdapter.viewHolder>() {
    class viewHolder(val itemBinding : TaskItemBinding):RecyclerView.ViewHolder(itemBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemBinding = TaskItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return viewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return tasks?.size ?:0
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.itemBinding.tittle.text=tasks?.get(position)?.tittle
        holder.itemBinding.description.text=tasks?.get(position)?.description

    }

    fun upDateTasks(tasks: List<Tasks>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }
    fun upDateTasks(position: Int) {
        notifyItemRemoved(position)
    }

}