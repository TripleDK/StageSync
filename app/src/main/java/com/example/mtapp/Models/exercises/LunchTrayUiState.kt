package com.example.mtapp.Models.exercises

data class LunchTrayUiState(
    val entree: MenuItem.EntreeItem? = null,
    val sideDish: MenuItem.SideDishItem? = null,
    val accompaniment: MenuItem.AccompanimentItem? = null,
    val itemTotalPrice: Double = 0.0,
    val orderTax: Double = 0.0,
    val orderTotalPrice: Double = 0.0
)