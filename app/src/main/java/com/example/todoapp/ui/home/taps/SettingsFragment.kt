package com.example.todoapp.ui.home.taps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import com.example.todoapp.databinding.SettingsFragmentBinding
import com.example.todoapp.ui.home.taps.taskslist.TasksListAdapter
import java.util.Locale

class SettingsFragment:Fragment() {
    lateinit var viewBinding:SettingsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=SettingsFragmentBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLanguageSpinner()
        initModeSpinner()
    }

    private fun initModeSpinner() {
        viewBinding.modeSpinner.onItemSelectedListener = object :OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0 )
                    return
                if (position == 1)
                {

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun initLanguageSpinner() {
        viewBinding.languageSpinner.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0)
                    return
                if (position == 1) {
                    val localeListCompat = LocaleListCompat.create(Locale("en"))
                    AppCompatDelegate.setApplicationLocales(localeListCompat)
                    return
                }
                if (position == 2) {
                    val localeListCompat = LocaleListCompat.create(Locale("ar"))
                    AppCompatDelegate.setApplicationLocales(localeListCompat)
                    return
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}