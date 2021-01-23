package com.vitaliidiadchenko.mykotkinapplication.screens.actorDetail

import android.content.Context
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
import com.bumptech.glide.Glide
import com.vitaliidiadchenko.mykotkinapplication.R
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.Movie
import com.vitaliidiadchenko.mykotkinapplication.screens.FragmentListener

class ActorDetailFragment : Fragment() {

    private var listener: FragmentListener? = null
    private var movie: Movie? = null
    private val viewModel: ActorDetailViewModel by viewModels { ActorDetailViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.actor_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? FragmentListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = arguments?.getParcelable("movie")
        view.findViewById<Button>(R.id.button_back_to_movie_detail_fragment).apply {
            setOnClickListener {
                movie?.let { it1 -> listener?.goToMoviesDetailsFragment(it1) }
            }
        }
        arguments?.getParcelable<Actor>("actor")?.let {
            movie?.let { it1 -> viewModel.getActor(it, it1.id) }
            viewModel.actorLiveData.observe(viewLifecycleOwner, {setupView(it)})
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setupView(actor: Actor) {
        view?.let {
            val poster = it.findViewById<ImageView>(R.id.img_actor)
            context?.let { context ->
                Glide.with(context)
                    .load(actor.picture)
                    .into(poster)
            }
            it.findViewById<TextView>(R.id.name_of_actor).text = actor.name
            it.findViewById<RatingBar>(R.id.rating_bar_actor).rating = actor.popularity!!
            it.findViewById<TextView>(R.id.date_of_birth).text = actor.birthday
            it.findViewById<TextView>(R.id.place_of_birth).text = actor.placeOfBirth
            it.findViewById<TextView>(R.id.biography).text = actor.biography
        }
    }

    companion object {
        fun newInstance(actor: Actor, movie: Movie): ActorDetailFragment =
            ActorDetailFragment().apply {
                val args = Bundle()
                args.putParcelable("movie", movie)
                args.putParcelable("actor", actor)
                arguments = args
            }
    }
}

