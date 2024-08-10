package com.example.mtapp.Models.exercises

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(
    @DrawableRes val photo: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
) {

}