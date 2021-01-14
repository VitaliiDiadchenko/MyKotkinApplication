package com.vitaliidiadchenko.mykotkinapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    var id: Int,
    var name: String,
    var picture: String,
    var placeOfBirth: String? = null,
    var popularity: Float = 0.0f,
    var birthday: String? = null,
    var biography: String? = null
) : Parcelable