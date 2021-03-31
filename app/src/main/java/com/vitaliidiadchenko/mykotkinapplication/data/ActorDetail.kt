package com.vitaliidiadchenko.mykotkinapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActorDetail(
    val id: Int,
    val name: String,
    val picture: String?,
    val placeOfBirth: String?,
    val popularity: Float,
    val birthday: String?,
    val biography: String
) : Parcelable