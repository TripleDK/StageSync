package com.example.mtapp.ui.screens.shows

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mtapp.data.Show
import com.example.mtapp.data.ShowsRepository

class ShowCreateViewModel(private val showsRepository: ShowsRepository) : ViewModel() {

    var showUiState by mutableStateOf(ShowFormState())
        private set

    fun updateUiState(showDetails: ShowDetails) {
        showUiState =
            ShowFormState(showDetails = showDetails, isEntryValid = validateInput(showDetails))
    }

    suspend fun saveShow() {
        if (validateInput()) {
            showsRepository.insertShow(showUiState.showDetails.toShow())
        }
    }

    private fun validateInput(uiState: ShowDetails = showUiState.showDetails): Boolean {
        return with(uiState) {
            name.isNotBlank()
        }
    }


}

data class ShowFormState(
    val showDetails: ShowDetails = ShowDetails(),
    val isEntryValid: Boolean = false
)

data class ShowDetails(
    val id: Int = 0,
    val name: String = "",
    val schedulePath: String? = ""
)

fun ShowDetails.toShow(): Show = Show(
    id = id,
    name = name,
    schedulePath = schedulePath //TODO: Show details process this path
)