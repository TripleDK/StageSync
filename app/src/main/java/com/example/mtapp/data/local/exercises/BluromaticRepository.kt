package com.example.mtapp.data.local.exercises

import kotlinx.coroutines.flow.Flow
import androidx.work.WorkInfo

interface BluromaticRepository {
    val outputWorkInfo: Flow<WorkInfo>
    fun applyBlur(blurlevel: Int)
    fun cancelWork()
}