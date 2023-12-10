package com.example.todoapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.todoapp.R
import com.example.todoapp.database.DataBase
import com.example.todoapp.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        DataBase.getInstance(applicationContext)

        startHomeActivity()
        finish()
    }

    private fun startHomeActivity() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                val intent = Intent(this@SplashActivity,HomeActivity::class.java)
                startActivity(intent)
            },2500
            )
    }
}