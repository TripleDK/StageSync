package com.example.mtapp.data

import kotlinx.coroutines.flow.Flow

class OfflineShowsRepository(private val showDao: ShowDao) : ShowsRepository {
    override fun getAllShowsStream(): Flow<List<Show>> = showDao.getAllShows()
    override fun getShowStream(id: Int): Flow<Show?> = showDao.getShow(id)

    override suspend fun insertShow(show: Show) = showDao.insert(show)
    override suspend fun deleteShow(show: Show) = showDao.delete(show)
    override suspend fun updateShow(show: Show) = showDao.update(show)
}