package com.example.mtapp.data

import kotlinx.coroutines.flow.Flow


interface ScenesRepository {
    fun getAllScenesInShowStream(showId: Int): Flow<List<Scene>>

    fun getSceneStream(id: Int): Flow<Scene?>

    suspend fun insertScene(scene: Scene)

    suspend fun deleteScene(scene: Scene)

    suspend fun updateScene(scene: Scene)


}