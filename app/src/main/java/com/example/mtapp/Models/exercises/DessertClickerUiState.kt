package com.example.mtapp.Models.exercises

data class DessertClickerUiState(
    val currentDessertImageId: Int = 0, // R.drawable.cupcake,
    val currentDessertPrice: Int = 5,
    val currentDessertIndex: Int = 0,
    val revenue: Int = 0,
    val dessertsSold: Int = 0
)