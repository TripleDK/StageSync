package com.example.mtapp.data

data class SceneState(
    val audioPath: String? = null,
    val playbackSpeed: Float = 1.0f,
    val toggledHeader: RehearsalOptions = RehearsalOptions.Script,
    val scriptPage: Int = 0,
    val scorePage: Int? = 0
)