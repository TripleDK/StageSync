package com.example.mtapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mtapp.exercises.InventoryApplication
import com.example.mtapp.ui.examples.home.HomeViewModel
import com.example.mtapp.ui.examples.item.ItemDetailsViewModel
import com.example.mtapp.ui.examples.item.ItemEditViewModel
import com.example.mtapp.ui.examples.item.ItemEntryViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemsRepository
            )
        }
        initializer {
            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
        }
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemsRepository
            )
        }

        initializer {
            HomeViewModel(inventoryApplication().container.itemsRepository)
        }
    }
}

fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)