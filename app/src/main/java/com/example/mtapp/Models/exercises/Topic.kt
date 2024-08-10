package com.example.mtapp.Models.exercises

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title: Int,
    val courseNumber: Int,
    @DrawableRes val logo: Int,
    @DrawableRes val icon: Int = 0
)