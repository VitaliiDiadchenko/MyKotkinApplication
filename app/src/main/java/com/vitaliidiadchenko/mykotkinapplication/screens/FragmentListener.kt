package com.vitaliidiadchenko.mykotkinapplication.screens

import com.vitaliidiadchenko.mykotkinapplication.data.Movie

interface FragmentListener {
    fun goToFragmentMoviesDetails(movie: Movie)
    fun goToFragmentMoviesList()
}