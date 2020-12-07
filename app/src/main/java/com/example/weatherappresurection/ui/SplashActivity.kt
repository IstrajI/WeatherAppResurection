package com.example.weatherappresurection.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.citylist.ui.CityListActivity
import com.example.weatherappresurection.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        //no requests are needed here so just showing splash
        object : Thread() {
            override fun run() {
                sleep(500)
                startActivity(Intent(this@SplashActivity, CityListActivity::class.java))
            }
        }.start()
    }
}