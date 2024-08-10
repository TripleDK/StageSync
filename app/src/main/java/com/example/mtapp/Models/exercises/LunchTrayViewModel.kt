package com.example.mtapp.Models.exercises

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mtapp.Models.exercises.MenuItem.EntreeItem
import com.example.mtapp.Models.exercises.MenuItem.AccompanimentItem
import com.example.mtapp.Models.exercises.MenuItem.SideDishItem
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class LunchTrayViewModel : ViewModel() {

    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(LunchTrayUiState())
    val uiState: StateFlow<LunchTrayUiState> = _uiState.asStateFlow()

    fun updateEntree(entree: EntreeItem) {
        val previousEntree = _uiState.value.entree
        updateItem(entree, previousEntree)
    }

    fun updateSideDish(sideDish: SideDishItem) {
        val previousSideDish = _uiState.value.sideDish
        updateItem(sideDish, previousSideDish)
    }

    fun updateAccompaniment(accompaniment: AccompanimentItem) {
        val previousAccompaniment = _uiState.value.accompaniment
        updateItem(accompaniment, previousAccompaniment)
    }

    fun resetOrder() {
        _uiState.value = LunchTrayUiState()
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax,
                entree = if (newItem is EntreeItem) newItem else currentState.entree,
                sideDish = if (newItem is SideDishItem) newItem else currentState.sideDish,
                accompaniment = if (newItem is AccompanimentItem) newItem else currentState.accompaniment
            )
        }
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}