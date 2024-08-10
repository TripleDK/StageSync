package com.example.mtapp.exercises

import android.app.Application
import com.example.mtapp.data.local.exercises.AppContainer
import com.example.mtapp.data.local.exercises.AppDataContainer

class InventoryApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}