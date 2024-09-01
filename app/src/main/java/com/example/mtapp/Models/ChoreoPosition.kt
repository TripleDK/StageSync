package com.example.mtapp.Models

import androidx.compose.ui.geometry.Offset
import java.util.UUID

data class ChoreoPosition(
    val id: UUID = UUID.randomUUID(),
    val keyFrames: List<KeyFrame> = emptyList(),
    val character: ShowCharacter,
    val castId: UUID? = null
)

data class KeyFrame(
    val time: Int,
    val relativePositionX: Float,
    val relativePositionY: Float
)