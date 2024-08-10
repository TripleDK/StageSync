package com.example.mtapp

import android.app.Application
import com.example.mtapp.data.AppContainer
import com.example.mtapp.data.DefaultAppContainer

class WaterMeApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}