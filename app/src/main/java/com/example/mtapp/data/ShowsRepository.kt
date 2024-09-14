package com.example.mtapp.data

import kotlinx.coroutines.flow.Flow


interface ShowsRepository {
    fun getAllShowsStream(): Flow<List<Show>>

    fun getShowStream(id: Int): Flow<Show?>

    suspend fun insertShow(show: Show)

    suspend fun deleteShow(show: Show)

    suspend fun updateShow(show: Show)


}