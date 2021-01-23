package com.vitaliidiadchenko.mykotkinapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    val id: Int,
    val name: String,
    val picture: String?,
    var placeOfBirth: String? = null,
    var popularity: Float? = 0.0f,
    var birthday: String? = null,
    var biography: String? = null
) : Parcelable