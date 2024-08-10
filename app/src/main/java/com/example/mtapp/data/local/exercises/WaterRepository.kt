package com.example.mtapp.data.local.exercises

import com.example.mtapp.Models.exercises.Plant
import java.util.concurrent.TimeUnit

interface WaterRepository {
    fun scheduleReminder(duration: Long, unit: TimeUnit, plantName: String)
    val plants: List<Plant>
}