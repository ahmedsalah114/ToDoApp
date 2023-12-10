package com.example.todoapp.ui.home.taps.taskslist

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.database.DataBase
import com.example.todoapp.database.Tasks
import com.example.todoapp.databinding.FragmentTasksListBinding
import com.example.todoapp.ui.clearTime
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.LocalDate

import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Calendar

import java.util.Locale


class TasksListFragment :Fragment(){
    lateinit var viewBinding:FragmentTasksListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding= FragmentTasksListBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTasks()

        toDeleteItem()
        initCalenderView()

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initCalenderView() {

        viewBinding.weekCalenderView.dayBinder=object : WeekDayBinder<DayViewContainer>{

            var selectedDate:LocalDate? =null
            override fun create(view: View): DayViewContainer {
                return DayViewContainer(view)
            }


            @SuppressLint("ResourceAsColor")
            override fun bind(container: DayViewContainer, data: WeekDay) {

                container.dayOfWeek.text = data.date.dayOfWeek.getDisplayName(
                    TextStyle.SHORT, Locale.getDefault()
                )
                container.numberOfMonth.text = data.date.dayOfMonth.toString()
                container.view.setOnClickListener {

                    if (selectedDate==data.date)
                    {
                        selectedDate = null
                        getTodosByDate(null)
                        viewBinding.weekCalenderView.notifyDateChanged(data.date)
                    }
                    else if (selectedDate==null)
                    {
                        selectedDate = data.date
                        viewBinding.weekCalenderView.notifyDateChanged(data.date)
                    }

                }
                val calendar=Calendar.getInstance()


                if (selectedDate==data.date) {
                    //change the color
                    container.dayOfWeek.setTextColor(resources.getColor(R.color.lightPrimary))
                    container.numberOfMonth.setTextColor(resources.getColor(R.color.lightPrimary))
                    //filter the tasks
                    calendar.set(
                        selectedDate?.year!!,
                        selectedDate?.monthValue?.minus(1)!!,
                        selectedDate?.dayOfMonth!!
                    )
                    calendar.clearTime()

                    getTodosByDate(calendar.timeInMillis)
                }
                if (selectedDate!=data.date)
                {
                    container.dayOfWeek.setTextColor(resources.getColor(R.color.black))
                    container.numberOfMonth.setTextColor(resources.getColor(R.color.black))
                }

            }

        }
        val currentDate =LocalDate.now()
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100).atStartOfMonth()  // Adjust as needed
        val endMonth = currentMonth.plusMonths(100).atEndOfMonth()  // Adjust as needed
        val firstDayOfWeek = firstDayOfWeekFromLocale() // Available from the library
        viewBinding.weekCalenderView.setup(startMonth, endMonth, firstDayOfWeek)
        viewBinding.weekCalenderView.scrollToWeek(currentDate)

    }
    fun getTodosByDate(selectedDate:Long?){
        val selectedDateList = if (selectedDate!=null)
        {
            DataBase.getInstance(requireContext())
                .dataBaseDao()
                .showWithDate(selectedDate)
        }else
        {
            DataBase.getInstance(requireContext())
                .dataBaseDao()
                .showAll()
        }
        tasksAdapter.upDateTasks(selectedDateList)
    }

    private fun toDeleteItem() {
        ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem:Tasks = tasksAdapter.tasks!![viewHolder.adapterPosition]
                val position =viewHolder.adapterPosition
                context?.let {  DataBase.getInstance( it )
                    .dataBaseDao()
                    .delete(deletedItem)
                    onTaskDeletedListener?.onTaskDeleted()

                }

            }
        }).attachToRecyclerView(viewBinding.recyclerView)
    }
    var onTaskDeletedListener:OnTaskDeletedListener ?=null
    fun interface OnTaskDeletedListener{
        fun onTaskDeleted()
    }

    override fun onStart() {
        super.onStart()
        loudTasks()
    }

     fun loudTasks() {
         context?.let { val tasks = DataBase.getInstance( it )
             .dataBaseDao()
             .showAll()
             tasksAdapter.upDateTasks(tasks)
         }
        }

    private val tasksAdapter = TasksListAdapter(null)
    private fun initTasks() {

        viewBinding.recyclerView.adapter=tasksAdapter
    }
}