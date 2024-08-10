package com.example.mtapp.data

import android.content.Context
import com.example.mtapp.data.local.exercises.BluromaticRepository
import com.example.mtapp.data.local.exercises.WaterRepository
import com.example.mtapp.data.local.exercises.WorkManagerBluromaticRepository
import com.example.mtapp.data.local.exercises.WorkManagerWaterRepository

interface AppContainer {
    val bluromaticRepository: BluromaticRepository
    val waterRepository: WaterRepository
    val stageSyncRepository: StageSyncRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    override val bluromaticRepository = WorkManagerBluromaticRepository(context)
    override val waterRepository = WorkManagerWaterRepository(context)
    override val stageSyncRepository = WorkManagerStageSyncRepository(context)
}