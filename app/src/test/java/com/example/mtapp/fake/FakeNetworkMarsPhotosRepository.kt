package com.example.mtapp.fake

import com.example.mtapp.Models.exercises.MarsPhoto
import com.example.mtapp.data.local.exercises.MarsPhotosRepository

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}