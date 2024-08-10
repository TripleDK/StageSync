package com.example.mtapp.data

import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Show

data class StageSyncUiState(
    val currentShow: Show? = null,
    val currentScene: Scene? = null
)