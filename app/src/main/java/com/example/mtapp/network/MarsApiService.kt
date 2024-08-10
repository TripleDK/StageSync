package com.example.mtapp.network

import com.example.mtapp.Models.exercises.MarsPhoto
import retrofit2.http.GET


interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
