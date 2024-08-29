package com.example.mtapp.data

import android.util.Log
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Show
import com.example.mtapp.Models.Song

data class StageSyncUiState(
    val currentShow: Show? = null,
    val currentScene: Scene? = null,
    val sceneStates: Map<Scene, SceneState> = emptyMap()
) {
    private val scenes: List<Scene>? = currentShow?.scenes

    fun updateCurrentShow(show: Show?): StageSyncUiState {
        return copy(
            currentShow = show,
            currentScene = null
        )
    }

    fun updateCurrentScene(scene: Scene): StageSyncUiState {
        val updatedSceneStates = sceneStates.toMutableMap()

        if (!updatedSceneStates.containsKey(scene)) {
            updatedSceneStates[scene] = SceneState(
                scorePage = if (scene is Song) {
                    scene.scoreStartPage ?: 0
                } else {
                    0
                },
                scriptPage = scene.startPage,
                audioPath = if (scene is Song) scene.masterAudio?.audioPath else ""
            )
        }
        return if (scenes != null && scenes.contains(scene)) {
            copy(
                currentScene = scene,
                sceneStates = updatedSceneStates
            )
        } else {
            this
        }
    }

    fun updateSceneState(scene: Scene, newSceneState: SceneState): StageSyncUiState {
        Log.v("test", "updated scene state!")
        return copy(sceneStates = sceneStates.toMutableMap().apply {
            this[scene] = newSceneState
        })
    }
}