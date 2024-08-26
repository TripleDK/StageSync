package com.example.mtapp.ui

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MediaPlayerViewModel(private val context: Context) : ViewModel() {
    var currentPosition by mutableStateOf(0)
        private set
    var duration by mutableStateOf(0)
        private set
    var isPlaying by mutableStateOf(false)

    private var mediaPlayer: MediaPlayer = MediaPlayer().apply {
        setOnCompletionListener { onCompletion() }
    }

    fun resetMediaPlayer(audioPath: String? = null) {
        currentPosition = 0
        if (mediaPlayer != null) {
            mediaPlayer!!.reset()
            if (!audioPath.isNullOrEmpty()) {
                mediaPlayer!!.setDataSource(audioPath)
                mediaPlayer!!.prepare()
            }
            mediaPlayer!!.seekTo(currentPosition)
            duration = mediaPlayer!!.duration
        }
        isPlaying = false
    }

    fun changeAudio(audioPath: String) {
        val currentAudioPosition = mediaPlayer.currentPosition
        mediaPlayer.reset()
        mediaPlayer.setDataSource(context, Uri.parse(audioPath))
        mediaPlayer.prepare()
        mediaPlayer.seekTo(currentAudioPosition)

        if (isPlaying)
            mediaPlayer.start()
    }

    fun startPlayback() {
        mediaPlayer?.start()
        isPlaying = true
        updateCurrentPosition()
    }

    fun pausePlayback() {
        mediaPlayer?.pause()
        isPlaying = false
    }

    fun seekTo(position: Int) {
        mediaPlayer?.seekTo(position)
        currentPosition = position
    }

    private fun onCompletion() {
        isPlaying = false
    }

    private fun updateCurrentPosition() {
        viewModelScope.launch {
            while (isPlaying && mediaPlayer?.isPlaying == true) {
                currentPosition = mediaPlayer?.currentPosition ?: 0
                delay(100)
            }
        }
    }
}

class MediaPlayerViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MediaPlayerViewModel::class.java)) {
            return MediaPlayerViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}