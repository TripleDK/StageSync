package com.example.mtapp.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mtapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "WaterReminderWorker"

class WaterReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val plantName = inputData.getString(nameKey)

        makePlantReminderNotification(
            applicationContext.resources.getString(R.string.time_to_water, plantName),
            applicationContext
        )

        return withContext(Dispatchers.IO)
        {
            return@withContext try {
                val plantName = inputData.getString(WaterReminderWorker.nameKey)


                Result.success()
            } catch (exception: Exception) {
                Log.e(
                    TAG,
                    "",
                    exception
                )
                Result.failure()
            }
        }
    }

    companion object {
        const val nameKey = "NAME"
    }
}