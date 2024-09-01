package com.example.mtapp.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//
//@Parcelize
//data class Role(
//    val character: ShowCharacter,
//    val castMember: CastMember
//) : Parcelable


@Parcelize
data class CastMember(
    val name: String,
    val roles: List<ShowCharacter> = emptyList()
) : Parcelable

@Parcelize
data class ShowCharacter(
    val name: String,
    val castMember: CastMember? = null
) : Parcelable


sealed interface Group : Parcelable

@Parcelize
data class CastGroup(
    val members: List<CastMember>
) : Group

@Parcelize
data class CharacterGroup(
    val name: String,
    val characters: List<ShowCharacter>
) : Group