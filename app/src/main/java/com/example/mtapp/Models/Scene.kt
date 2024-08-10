package com.example.mtapp.Models

import android.os.Parcelable
import androidx.annotation.StringRes
import com.example.mtapp.data.SceneType
import kotlinx.parcelize.Parcelize

@Parcelize
data class Scene(
    @StringRes var name: Int,
    var type: SceneType,
    var scriptPath: String? = null,
    var scorePath: String? = null,
    var startPage: Int,
    var scoreStartPage: Int? = null,
) : Parcelable