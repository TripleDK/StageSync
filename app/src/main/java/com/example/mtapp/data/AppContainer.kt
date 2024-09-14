package com.example.mtapp.data

import android.content.Context
import com.example.mtapp.data.local.exercises.BluromaticRepository
import com.example.mtapp.data.local.exercises.WaterRepository
import com.example.mtapp.data.local.exercises.WorkManagerBluromaticRepository
import com.example.mtapp.data.local.exercises.WorkManagerWaterRepository

interface AppContainer {
    val bluromaticRepository: BluromaticRepository
    val waterRepository: WaterRepository
    val showsRepository: ShowsRepository
    val scenesRepository: ScenesRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    override val bluromaticRepository = WorkManagerBluromaticRepository(context)
    override val waterRepository = WorkManagerWaterRepository(context)

    override val showsRepository: ShowsRepository by lazy {
        OfflineShowsRepository(ShowDatabase.getDatabase(context).showDao())
    }
    override val scenesRepository: ScenesRepository by lazy {
        OfflineScenesRepository(SceneDatabase.getDatabase(context).sceneDao())
    }

}