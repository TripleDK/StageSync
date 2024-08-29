package com.example.mtapp.Models

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    @StringRes val name: Int,
    val scenes: List<Scene>,
    var schedulePath: String? = null
) : Parcelable