package com.example.mtapp.data

import android.content.Context
import com.example.mtapp.Models.Show
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WorkManagerStageSyncRepository(context: Context) : StageSyncRepository {
    private var isInitialized = false

    init {
        runBlocking {
            initializeDataSource(context)
        }
    }

    override val shows: List<Show>
        get() {
            check(isInitialized) { "DataSource is not initialized!" }
            return DataSource.shows
        }

    private suspend fun initializeDataSource(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            DataSource.initialize(context)
            isInitialized = true
        }.join()
    }
}