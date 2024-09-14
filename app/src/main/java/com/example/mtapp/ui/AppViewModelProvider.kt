package com.example.mtapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mtapp.StageSyncApplication
import com.example.mtapp.exercises.InventoryApplication
import com.example.mtapp.ui.examples.home.HomeViewModel
import com.example.mtapp.ui.examples.item.ItemDetailsViewModel
import com.example.mtapp.ui.examples.item.ItemEditViewModel
import com.example.mtapp.ui.examples.item.ItemEntryViewModel
import com.example.mtapp.ui.screens.scenes.SceneListViewModel
import com.example.mtapp.ui.screens.shows.ShowCreateViewModel
import com.example.mtapp.ui.screens.shows.ShowListViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ShowCreateViewModel(
                stageSyncApplication().container.showsRepository
            )
        }

        initializer {
            ShowListViewModel(
                stageSyncApplication().container.showsRepository
            )
        }

        initializer {
            SceneListViewModel(
                this.createSavedStateHandle(),
                stageSyncApplication().container.scenesRepository,
                stageSyncApplication().container.showsRepository
            )
        }


        //////ITEM EXERCISES
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

fun CreationExtras.stageSyncApplication(): StageSyncApplication =
    (this[APPLICATION_KEY] as StageSyncApplication)

fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)