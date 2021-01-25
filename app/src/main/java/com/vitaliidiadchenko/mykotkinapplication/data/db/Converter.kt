package com.vitaliidiadchenko.mykotkinapplication.data.db

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromListOfGeners(listOfGeners: List<String>): String {
        return listOfGeners.joinToString(",")
    }

    @TypeConverter
    fun toListOfGeners(flatGenerList: String): List<String> {
        return flatGenerList.split(",")
    }
}