package com.vitaliidiadchenko.mykotkinapplication.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(val id: Int, val name: String): Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }
}

