package com.example.mtapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Show
import com.example.mtapp.StageSyncApplication
import com.example.mtapp.data.RehearsalOptions
import com.example.mtapp.data.StageSyncRepository
import com.example.mtapp.data.StageSyncUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StageSyncViewModel(private val stageSyncRepository: StageSyncRepository) : ViewModel() {


    private val _uiState = MutableStateFlow(StageSyncUiState())
    val uiState: StateFlow<StageSyncUiState> = _uiState.asStateFlow()

    internal val shows = stageSyncRepository.shows

    fun setSelectedShow(show: Show) {
        _uiState.update { currentState ->
            currentState.copy(
                currentShow = show,
                currentScene = null
            )
        }
    }

    fun setSelectedScene(scene: Scene) {
        _uiState.update { currentState ->
            currentState.copy(
                currentShow = _uiState.value.currentShow,
                currentScene = scene
            )
        }
    }

    fun setNextScene() {
        val currentShow = _uiState.value.currentShow
        val currentScene = _uiState.value.currentScene
        val currentSceneIndex = currentShow?.scenes?.indexOf(currentScene) ?: return

        if (currentSceneIndex < currentShow.scenes.size?.minus(1) ?: return) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentShow = currentShow,
                    currentScene = currentShow.scenes[currentSceneIndex + 1]
                )
            }
        } else {
            Log.v("MainActivity", "Already at last scene!")
        }
    }

    fun setPrevScene() {
        val currentShow = _uiState.value.currentShow
        val currentScene = _uiState.value.currentScene
        val currentSceneIndex = currentShow?.scenes?.indexOf(currentScene) ?: return

        if (currentSceneIndex > 0) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentShow = currentShow,
                    currentScene = currentShow.scenes[currentSceneIndex - 1]
                )
            }
        } else {
            Log.v("MainActivity", "Already at first scene!")
        }
    }

    fun toggleRehearsalOptions(options: RehearsalOptions) {
        _uiState.update { currentState ->
            currentState.copy(
                currentHeader = options
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val stageSyncRepository =
                    (this[APPLICATION_KEY] as StageSyncApplication).container.stageSyncRepository
                StageSyncViewModel(
                    stageSyncRepository = stageSyncRepository
                )
            }
        }
    }

}
