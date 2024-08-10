package com.example.mtapp.Models.exercises

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.mtapp.R
import com.example.mtapp.data.local.exercises.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel : ViewModel() {
    private val TAG = "DessertViewModel"

    private val _uiState = MutableStateFlow(DessertClickerUiState())
    val uiState: StateFlow<DessertClickerUiState> = _uiState.asStateFlow()

    var allDessert = Datasource().dessertList


    /**
     * Share desserts sold information using ACTION_SEND intent
     */
    fun shareSoldDessertsInformation(
        intentContext: Context,
    ) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                intentContext.getString(
                    R.string.share_text,
                    uiState.value.dessertsSold,
                    uiState.value.revenue
                )
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        try {
            ContextCompat.startActivity(intentContext, shareIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                intentContext,
                intentContext.getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Determine which dessert to show.
     */
    fun determineDessertToShow(
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = allDessert.first()

        for (dessert in allDessert) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

    fun onDessertClicked() {
        // Update the revenue
        val revenue = _uiState.value.currentDessertPrice.plus(_uiState.value.revenue)
        val dessertsSold = _uiState.value.dessertsSold + 1

        // Show the next dessert
        val dessertToShow = determineDessertToShow(dessertsSold)
        updateGameState(dessertToShow, revenue, dessertsSold)
    }

    private fun updateGameState(dessertToShow: Dessert, revenue: Int, dessertsSold: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentDessertImageId = dessertToShow.imageId,
                dessertsSold = dessertsSold,
                revenue = revenue,
                currentDessertPrice = dessertToShow.price
            )
        }

    }

}