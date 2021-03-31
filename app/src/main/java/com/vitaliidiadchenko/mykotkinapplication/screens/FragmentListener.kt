package com.vitaliidiadchenko.mykotkinapplication.screens

import com.vitaliidiadchenko.mykotkinapplication.data.Actor

interface FragmentListener {
    fun goToMoviesDetailsFragment(movieId: Int)
    fun goToMoviesListFragment()
    fun goToActorDetailFragment(actor: Actor)
}