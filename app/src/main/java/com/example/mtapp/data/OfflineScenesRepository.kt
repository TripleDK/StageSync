package com.example.mtapp.data

import kotlinx.coroutines.flow.Flow

class OfflineScenesRepository(private val sceneDao: SceneDao) : ScenesRepository {
    override fun getAllScenesInShowStream(showId: Int): Flow<List<Scene>> =
        sceneDao.getScenesForShow(showId)

    override fun getSceneStream(id: Int): Flow<Scene?> = sceneDao.getScene(id)

    override suspend fun insertScene(scene: Scene) = sceneDao.insert(scene)
    override suspend fun deleteScene(scene: Scene) = sceneDao.delete(scene)
    override suspend fun updateScene(scene: Scene) = sceneDao.update(scene)
}