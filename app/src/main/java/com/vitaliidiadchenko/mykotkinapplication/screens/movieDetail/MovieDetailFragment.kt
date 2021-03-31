package com.vitaliidiadchenko.mykotkinapplication.screens.movieDetail

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialContainerTransform
import com.vitaliidiadchenko.mykotkinapplication.R
import com.vitaliidiadchenko.mykotkinapplication.adapter.ActorAdapter
import com.vitaliidiadchenko.mykotkinapplication.adapter.OnActorItemClickListener
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

class MovieDetailFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private val viewModel: MovieDetailViewModel by viewModels { MovieDetailViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setAnimation()
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_back)?.apply {
            setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        recyclerView = view.findViewById(R.id.recycler_view_list_actors)
        recyclerView?.adapter = ActorAdapter(actorListener)
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView?.hasFixedSize()
        val movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).selectedMovie
        viewModel.getActors(movie.id)
        viewModel.actors.observe(viewLifecycleOwner, { setupActors(it) })
        viewModel.getMovie(movie.id)
        viewModel.movie.observe(viewLifecycleOwner, { setupView(it) })
    }

    private val actorListener = object : OnActorItemClickListener {
        override fun onClick(actor: Actor) {
            openActorDetailFragment(actor)
        }
    }

    private fun openActorDetailFragment(actor: Actor) {
        this.findNavController().navigate(MovieDetailFragmentDirections.actionToActorDetailFragment(actor))
    }

    private fun setupView(movie: Movie) {
        view?.let {
            // set backdrop poster
            val backdrop = it.findViewById<ImageView>(R.id.image_poster)
            context?.let { context ->
                Glide.with(context)
                    .load(movie.backdrop)
                    .into(backdrop)
            }

            it.findViewById<TextView>(R.id.movieTitle).text = movie.title
            it.findViewById<TextView>(R.id.tag_line).text =
                movie.genres.joinToString(separator = ", ")
            it.findViewById<TextView>(R.id.description_of_movie).text = movie.overview
            it.findViewById<RatingBar>(R.id.ratingBar).rating = (movie.ratings / 2)
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
            (recyclerView?.adapter as? ActorAdapter)?.apply {
                onBindListActor(actors)
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun setAnimation() {
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.main_container
            duration = ANIMATION_DURATION
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().getColor(R.attr.colorSurface))
        }
    }

    companion object {
        const val ANIMATION_DURATION = 3_000L
    }
}