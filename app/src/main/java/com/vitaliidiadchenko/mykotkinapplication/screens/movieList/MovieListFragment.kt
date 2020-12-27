package com.vitaliidiadchenko.mykotkinapplication.screens.movieList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vitaliidiadchenko.mykotkinapplication.screens.FragmentListener
import com.vitaliidiadchenko.mykotkinapplication.R
import com.vitaliidiadchenko.mykotkinapplication.adapter.MovieViewHolderAdapter
import com.vitaliidiadchenko.mykotkinapplication.adapter.OnPosterCardClickListener
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

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
        recyclerView?.adapter = MovieViewHolderAdapter(movieListener)
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
            listener?.goToFragmentMoviesDetails(movie)
        }
    }

    private fun setMovies() {
        viewModel.moviesData.observe(viewLifecycleOwner, { movies ->
            updateData(movies)
        })
        viewModel.state.observe(viewLifecycleOwner, { status ->
            when (status) {
                is State.Init, is State.Success -> progressBar?.visibility = View.INVISIBLE
                is State.Error -> progressBar?.visibility = View.INVISIBLE
                is State.Loading -> progressBar?.visibility = View.VISIBLE
            }
        })
    }

    private fun updateData(movies : List<Movie>) {
        (recyclerView?.adapter as? MovieViewHolderAdapter)?.apply {
            bindMovies(movies)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}