package com.vitaliidiadchenko.mykotkinapplication

import com.vitaliidiadchenko.mykotkinapplication.data.Movie

interface FragmentListener {
    fun goToFragmentMoviesDetails(movie: Movie)
    fun goToFragmentMoviesList()
}