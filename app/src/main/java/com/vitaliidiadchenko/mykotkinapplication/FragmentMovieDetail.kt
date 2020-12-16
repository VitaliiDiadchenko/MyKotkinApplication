package com.vitaliidiadchenko.mykotkinapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vitaliidiadchenko.mykotkinapplication.adapter.ActorViewHolderAdapter
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

class FragmentMovieDetail : Fragment() {

    private var listener: FragmentListener? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        view?.findViewById<Button>(R.id.button_back)?.apply {
            setOnClickListener {
                listener?.goToFragmentMoviesList()
            }
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view_list_actors)
        recyclerView?.adapter = ActorViewHolderAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView?.hasFixedSize()
        arguments?.getParcelable<Movie>("movie")?.let { movie ->
            setupView(movie)
            Log.i("movie", movie.id.toString())
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
        view?.run {
            // set backdrop poster
            val backdrop = findViewById<ImageView>(R.id.image_poster)
            Glide.with(context)
                .load(movie.backdrop)
                .into(backdrop)

            findViewById<TextView>(R.id.movieTitle).text = movie.title
            findViewById<TextView>(R.id.tag_line).text =
                movie.genres.joinToString(separator = ", ") { it.name }
            findViewById<TextView>(R.id.storyline).text = movie.overview
            findViewById<RatingBar>(R.id.ratingBar).rating = (movie.ratings / 2)
            findViewById<TextView>(R.id.textReview).text =
                resources.getQuantityString(R.plurals.review, 0, 0) //json doesn't have review
            //set ageRating
            val ageRating = findViewById<TextView>(R.id.ageRating)
            if (movie.adult) {
                ageRating.text = resources.getString(R.string.adult_age_rating)
            } else {
                ageRating.visibility = View.INVISIBLE
            }
            //set actor's images to recyclerView
            val actors = movie.actors
            if (actors.isNotEmpty()) {
                (recyclerView?.adapter as ActorViewHolderAdapter).apply {
                    bindActors(actors)
                }
            } else {
                recyclerView?.visibility = View.INVISIBLE
            }
        }
    }

    companion object {
        fun newInstance(movie: Movie): FragmentMovieDetail =
            FragmentMovieDetail().apply {
                val args = Bundle()
                args.putParcelable("movie", movie)
                arguments = args
            }

    }
}