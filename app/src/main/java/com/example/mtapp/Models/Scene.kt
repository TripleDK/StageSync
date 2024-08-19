package com.example.mtapp.Models

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
open class Scene(
    @StringRes open var name: Int,
    open var scriptPath: String? = null,
    open var scorePath: String? = null,
    open var startPage: Int,
    open var endPage: Int,
    open var scoreStartPage: Int? = null,
    open var scoreEndPage: Int? = null
) : Parcelable


@Parcelize
data class Song(
    @StringRes override var name: Int,
    override var scriptPath: String? = null,
    override var scorePath: String? = null,
    override var startPage: Int,
    override var endPage: Int,
    override var scoreStartPage: Int? = null,
    override var scoreEndPage: Int? = null,
    var masterAudio: AudioObject? = null,
    var tracks: List<AudioObject>? = null
) : Scene(
    name = name,
    scriptPath = scriptPath,
    scorePath = scorePath,
    startPage = startPage,
    endPage = endPage,
    scoreStartPage = scoreStartPage,
    scoreEndPage = scoreEndPage
)

@Parcelize
data class AudioObject(
    @StringRes var name: Int,
    var audioPath: String? = null
) : Parcelable