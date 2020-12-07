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

class FragmentMovieList : Fragment() {

    private var recyclerView: RecyclerView? = null

    private var listener: FragmentListener? = null

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
        updateData()
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
            listener?.goToFragmentMoviesDetails()
        }
    }

    private fun updateData() {
        (recyclerView?.adapter as? MovieViewHolderAdapter)?.apply {
            bindMovies(moviesList)
        }
    }

    companion object {
        private val movie1: Movie = Movie(
            "13+", R.drawable.img_small_poster, "Avengers:End Game", false,
            4, 125, "Action, Adventure, Fantasy", 137
        )
        private val movie2: Movie = Movie(
            "16+", R.drawable.img_small_poster2, "Tenet", true,
            5, 98, "Action, Sci-Fi, Thriller", 97
        )
        private val movie3: Movie = Movie(
            "13+", R.drawable.img_small_poster3, "Black Widow", false,
            4, 38, "Action, Adventure, Sci-Fi", 102
        )
        private val movie4: Movie = Movie(
            "13+", R.drawable.img_small_poster4, "Wonder Woman 1984", false,
            5, 74, "Action, Adventure, Fantasy", 120
        )
        private var moviesList = listOf(movie1, movie2, movie3, movie4)
    }
}