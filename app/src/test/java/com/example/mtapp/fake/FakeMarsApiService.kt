package com.example.mtapp.fake

import com.example.mtapp.Models.exercises.MarsPhoto
import com.example.mtapp.network.MarsApiService

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}