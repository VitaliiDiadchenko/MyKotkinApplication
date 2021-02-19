package com.vitaliidiadchenko.mykotkinapplication.screens.actorDetail

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
import com.vitaliidiadchenko.mykotkinapplication.data.ActorDetail

class ActorDetailFragment : Fragment() {

    private val viewModel: ActorDetailViewModel by viewModels { ActorDetailViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.actor_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_back_to_movie_detail_fragment).apply {
            setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        arguments?.getParcelable<Actor>("actor")?.let { actor ->
            viewModel.getActorDetail(actor.id)
            viewModel.actorLiveData.observe(viewLifecycleOwner, {setupView(it)})
        }
    }

    private fun setupView(actorDetail: ActorDetail) {
        view?.let {
            val poster = it.findViewById<ImageView>(R.id.img_actor)
            context?.let { context ->
                Glide.with(context)
                    .load(actorDetail.picture)
                    .into(poster)
            }
            it.findViewById<TextView>(R.id.name_of_actor).text = actorDetail.name
            it.findViewById<RatingBar>(R.id.rating_bar_actor).rating = actorDetail.popularity
            it.findViewById<TextView>(R.id.date_of_birth).text = actorDetail.birthday
            it.findViewById<TextView>(R.id.place_of_birth).text = actorDetail.placeOfBirth
            it.findViewById<TextView>(R.id.biography).text = actorDetail.biography
        }
    }

    companion object {
        fun newInstance(actor: Actor): ActorDetailFragment =
            ActorDetailFragment().apply {
                val args = Bundle()
                args.putParcelable("actor", actor)
                arguments = args
            }
    }
}

