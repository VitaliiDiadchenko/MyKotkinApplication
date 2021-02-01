package com.vitaliidiadchenko.mykotkinapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vitaliidiadchenko.mykotkinapplication.R
import com.vitaliidiadchenko.mykotkinapplication.data.Actor

class ActorAdapter(
    private val listActors: List<Actor>,
    private var onActorItemClickListener: OnActorItemClickListener
) : RecyclerView.Adapter<ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun getItemCount(): Int = listActors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(listActors[position])
        holder.itemView.setOnClickListener {
            onActorItemClickListener.onClick(listActors[position])
        }
    }
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatar = itemView.findViewById<ImageView>(R.id.image_actor)
    private val name = itemView.findViewById<TextView>(R.id.text_under_img_first_actor)

    fun onBind(actor: Actor) {

        val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .fallback(R.drawable.ic_avatar_placeholder)
            .circleCrop()
        Glide.with(context)
            .load(actor.picture)
            .apply(imageOption)
            .into(avatar)
        name.text = actor.name
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnActorItemClickListener {
    fun onClick(actor: Actor)
}