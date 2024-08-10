package com.example.mtapp.data.local.exercises

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mtapp.KEY_WATER_DURATION
import com.example.mtapp.KEY_WATER_UNIT
import com.example.mtapp.Models.exercises.Plant
import com.example.mtapp.workers.WaterReminderWorker
import java.util.concurrent.TimeUnit

class WorkManagerWaterRepository(context: Context) : WaterRepository {
    private val workManager = WorkManager.getInstance(context)

    override val plants: List<Plant>
        get() = WaterMeDataSource.plants

    override fun scheduleReminder(duration: Long, unit: TimeUnit, plantName: String) {
        val data = Data.Builder()
        data.putString(WaterReminderWorker.nameKey, plantName)

        val reminderBuilder = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInitialDelay(duration, unit)
            .setInputData(data.build())
            .build()

        workManager.enqueueUniqueWork(
            plantName + " - " + duration,
            ExistingWorkPolicy.REPLACE,
            reminderBuilder
        )
    }


    private fun createInputDataForWorkRequest(duration: Long, unit: TimeUnit): Data {
        val builder = Data.Builder()
        builder.putLong(KEY_WATER_DURATION, duration)
            .putString(KEY_WATER_UNIT, unit.toString())
        return builder.build()
    }
}

