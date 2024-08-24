package com.example.mtapp.utils

fun formatTime(milliseconds: Float): String {
    val totalSeconds = (milliseconds / 1000).toInt()  // Convert milliseconds to total seconds
    val minutes = totalSeconds / 60  // Get the number of minutes
    val seconds = totalSeconds % 60  // Get the remaining seconds
    return String.format("%02d:%02d", minutes, seconds)
}