package com.example.todoapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityHomeBinding
import com.example.todoapp.ui.home.taps.SettingsFragment
import com.example.todoapp.ui.home.taps.taskslist.TasksListFragment
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeBinding
    var tasksListFragmentRef : TasksListFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initBottomNavigationView()
        initAddTask()
        initDeleteTask()

    }

    private fun initDeleteTask() {
        TasksListFragment.OnTaskDeletedListener{
            tasksListFragmentRef?.loudTasks()
        }
    }

    private fun initAddTask() {
        viewBinding.floutingAdd.setOnClickListener {
            val addTasksheet =AddTaskFragment()
            addTasksheet.onTaskAddedListener= AddTaskFragment.OnTaskAddedListener {
                Snackbar.make(viewBinding.root,"task added",Snackbar.LENGTH_LONG)
                    .show()
                tasksListFragmentRef?.loudTasks()
            }
            addTasksheet.show(supportFragmentManager,"")
        }
    }

    private fun initBottomNavigationView() {
        viewBinding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_tasks_list ->{
                    tasksListFragmentRef = TasksListFragment()
                    showFragment(tasksListFragmentRef!!)
                }

                R.id.navigation_tasks_setting->{
                    showFragment(SettingsFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        viewBinding.bottomNavigationView.selectedItemId = R.id.navigation_tasks_list
    }
    private fun showFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}