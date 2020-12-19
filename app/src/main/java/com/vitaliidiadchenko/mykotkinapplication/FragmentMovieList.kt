package com.vitaliidiadchenko.mykotkinapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vitaliidiadchenko.mykotkinapplication.adapter.MovieViewHolderAdapter
import com.vitaliidiadchenko.mykotkinapplication.adapter.OnPosterCardClickListener
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.data.loadMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentMovieList : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var listener: FragmentListener? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private val movieListener = object : OnPosterCardClickListener {
        override fun onClick(movie: Movie) {
            listener?.goToFragmentMoviesDetails(movie)
        }
    }

    private fun setMovies() {
        scope.launch {
            val movies = loadMovies(requireContext())
            updateData(movies)
        }
    }

    private fun updateData(movies : List<Movie>) {
        (recyclerView?.adapter as? MovieViewHolderAdapter)?.apply {
            movies.let{bindMovies(it)}
        }
    }
}