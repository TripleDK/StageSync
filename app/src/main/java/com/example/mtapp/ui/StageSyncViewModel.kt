package com.example.mtapp.ui;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Show
import com.example.mtapp.StageSyncApplication
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
