package com.example.mtapp.Models

import android.os.Parcelable
import androidx.annotation.StringRes
import com.example.mtapp.data.SceneState
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
open class Scene(
    @StringRes open var name: Int,
    open var scriptPath: String? = null,
    open var scorePath: String? = null,
    open var startPage: Int,
    open var endPage: Int,
    open var sceneState: @RawValue SceneState = SceneState() //TODO: Have a state per scene, so it remembers where you left off on each scene
) : Parcelable


@Parcelize
data class Song(
    @StringRes override var name: Int,
    override var scriptPath: String? = null,
    override var scorePath: String? = null,
    override var startPage: Int,
    override var endPage: Int,
    var scoreStartPage: Int? = null,
    var scoreEndPage: Int? = null,
    var masterAudio: AudioObject? = null,
    var tracks: List<AudioObject>? = null
) : Scene(
    name = name,
    scriptPath = scriptPath,
    scorePath = scorePath,
    startPage = startPage,
    endPage = endPage
)

@Parcelize
data class AudioObject(
    @StringRes var name: Int,
    var audioPath: String? = null
) : Parcelable