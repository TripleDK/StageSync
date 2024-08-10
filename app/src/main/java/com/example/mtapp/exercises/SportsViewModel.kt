package com.example.mtapp.exercises

import androidx.lifecycle.ViewModel
import com.example.mtapp.Models.exercises.Sport
import com.example.mtapp.data.local.exercises.LocalSportsDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SportsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        SportsUiState(
            sportsList = LocalSportsDataProvider.getSportsData(),
            currentSport = LocalSportsDataProvider.getSportsData().getOrElse(0) {
                LocalSportsDataProvider.defaultSport
            }
        )

    )
    val uiState: StateFlow<SportsUiState> = _uiState

    fun updateCurrentSport(selectedSport: Sport) {
        _uiState.update {
            it.copy(currentSport = selectedSport)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }

}

data class SportsUiState(
    val sportsList: List<Sport> = emptyList(),
    val currentSport: Sport = LocalSportsDataProvider.defaultSport,
    val isShowingListPage: Boolean = true
)