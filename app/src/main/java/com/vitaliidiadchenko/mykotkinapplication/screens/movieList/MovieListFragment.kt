package com.vitaliidiadchenko.mykotkinapplication.screens.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialContainerTransform
import com.vitaliidiadchenko.mykotkinapplication.App
import com.vitaliidiadchenko.mykotkinapplication.R
import com.vitaliidiadchenko.mykotkinapplication.adapter.MovieAdapter
import com.vitaliidiadchenko.mykotkinapplication.adapter.OnPosterCardClickListener
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.screens.State

class MovieListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private val viewModel: MovieListViewModel by viewModels { MovieListViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.recycler_view_list_movies)
        recyclerView?.adapter = MovieAdapter(movieListener)
        recyclerView?.layoutManager = GridLayoutManager(context, 2)
        recyclerView?.hasFixedSize()
        setMovies()
    }

    private val movieListener = object : OnPosterCardClickListener {
        override fun onClick(cardView: View, movie: Movie) {
            openMovieDetailFragment(cardView, movie)
        }
    }

    private fun openMovieDetailFragment(cardView: View, movie: Movie) {
        exitTransition = MaterialContainerTransform().apply { duration = ANIMATION_DURATION }
        reenterTransition = MaterialContainerTransform().apply { duration = ANIMATION_DURATION }
        val movieDetailTransitionName = getString(R.string.movie_detail_fragment_transition)
        val extras = FragmentNavigatorExtras(cardView to movieDetailTransitionName)
        this.findNavController().navigate(MovieListFragmentDirections.actionToMovieDetailFragment(movie), extras)
    }

    private fun setMovies() {
        viewModel.moviesData.observe(viewLifecycleOwner, { movies ->
            updateData(movies)
        })
        viewModel.state.observe(viewLifecycleOwner, { status ->
            when (status) {
                is State.Init, is State.Success -> progressBar?.visibility = View.INVISIBLE
                is State.Error -> {
                    progressBar?.visibility = View.INVISIBLE
                    Toast.makeText(App.context(), "Error getting data", Toast.LENGTH_SHORT).show()
                }
                is State.Loading -> progressBar?.visibility = View.VISIBLE
            }
        })
    }

    private fun updateData(movies: List<Movie>) {
        (recyclerView?.adapter as? MovieAdapter)?.apply {
            bindMovies(movies)
        }
    }

    companion object {
        const val ANIMATION_DURATION = 3_000L
    }
}