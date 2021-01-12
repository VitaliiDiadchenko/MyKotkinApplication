package com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vitaliidiadchenko.mykotkinapplication.R
import com.vitaliidiadchenko.mykotkinapplication.adapter.ActorAdapter
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.screens.FragmentListener

class MovieDetailFragment : Fragment() {

    private var listener: FragmentListener? = null
    private var recyclerView: RecyclerView? = null
    private val viewModel: MovieDetailViewModel by viewModels { MovieDetailViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        view?.findViewById<Button>(R.id.button_back)?.apply {
            setOnClickListener {
                listener?.goToMoviesListFragment()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView?.hasFixedSize()
        arguments?.getParcelable<Movie>("movie")?.let { movie ->
            viewModel.getActors(movieId = movie.id)
            viewModel.actors.observe(viewLifecycleOwner, {setupActors(it)})
            setupView(movie)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? FragmentListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setupView(movie: Movie) {
        view?.let {
            // set backdrop poster
            val backdrop = it.findViewById<ImageView>(R.id.image_actor)
            context?.let { context ->
                Glide.with(context)
                    .load(movie.backdrop)
                    .into(backdrop)
            }

            it.findViewById<TextView>(R.id.name_of_actor).text = movie.title
            it.findViewById<TextView>(R.id.date_of_birth).text =
                movie.genres.joinToString(separator = ", ")
            it.findViewById<TextView>(R.id.title_biography).text = movie.overview
            it.findViewById<RatingBar>(R.id.rating_bar_actor).rating = (movie.ratings / 2)
            it.findViewById<TextView>(R.id.textReview).text =
                resources.getQuantityString(R.plurals.review, 0, 0) //json doesn't have review
            //set ageRating
            if (movie.adult) {
                with(it.findViewById<TextView>(R.id.ageRating)) {
                    text = resources.getString(R.string.adult_age_rating)
                    visibility = View.VISIBLE
                }
            }

        }
    }

    //set actor's images to recyclerView
    private fun setupActors(actors: List<Actor>) {
        if (actors.isNotEmpty()) {
            view?.let {
                it.findViewById<TextView>(R.id.text_cast).isVisible = true
                it.findViewById<RecyclerView>(R.id.recycler_view_list_actors).apply {
                    isVisible = true
                    adapter = ActorAdapter(actors)
                }
            }
        }
    }

    companion object {
        fun newInstance(movie: Movie): MovieDetailFragment =
            MovieDetailFragment().apply {
                val args = Bundle()
                args.putParcelable("movie", movie)
                arguments = args
            }
    }
}