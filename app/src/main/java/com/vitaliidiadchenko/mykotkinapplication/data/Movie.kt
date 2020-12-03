package com.vitaliidiadchenko.mykotkinapplication.data

data class Movie(
    val ageRating: String,
    val poster: Int,
    val title: String,
    val like: Boolean,
    val rating: Int,
    val review: String,
    val tagLine: String,
    val runTime: String
    )