package com.vitaliidiadchenko.mykotlinapplication.screens

import com.vitaliidiadchenko.mykotlinapplication.data.Actor

interface FragmentListener {
    fun goToMoviesDetailsFragment(movieId: Int)
    fun goToMoviesListFragment()
    fun goToActorDetailFragment(actor: Actor)
}