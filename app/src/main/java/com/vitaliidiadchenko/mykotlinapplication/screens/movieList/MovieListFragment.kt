package com.vitaliidiadchenko.mykotlinapplication.screens.movieList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vitaliidiadchenko.mykotlinapplication.App
import com.vitaliidiadchenko.mykotlinapplication.screens.FragmentListener
import com.vitaliidiadchenko.mykotlinapplication.R
import com.vitaliidiadchenko.mykotlinapplication.adapter.MovieAdapter
import com.vitaliidiadchenko.mykotlinapplication.adapter.OnPosterCardClickListener
import com.vitaliidiadchenko.mykotlinapplication.data.Movie
import com.vitaliidiadchenko.mykotlinapplication.screens.State

class MovieListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var listener: FragmentListener? = null
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
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.recycler_view_list_movies)
        recyclerView?.adapter = MovieAdapter(movieListener)
        recyclerView?.layoutManager = GridLayoutManager(context, 2)
        recyclerView?.hasFixedSize()
        setMovies()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? FragmentListener
    }

    private val movieListener = object : OnPosterCardClickListener {
        override fun onClick(movie: Movie) {
            listener?.goToMoviesDetailsFragment(movie.id)
        }
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

    private fun updateData(movies : List<Movie>) {
        (recyclerView?.adapter as? MovieAdapter)?.apply {
            bindMovies(movies)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}