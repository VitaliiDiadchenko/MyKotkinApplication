package com.vitaliidiadchenko.mykotkinapplication.screens

import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

interface FragmentListener {
    fun goToMoviesDetailsFragment(movie: Movie)
    fun goToMoviesListFragment()
    fun goToActorDetailFragment(actor: Actor)
}