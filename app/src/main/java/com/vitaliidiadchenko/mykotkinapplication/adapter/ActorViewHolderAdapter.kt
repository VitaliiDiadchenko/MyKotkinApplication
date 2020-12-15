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

class ActorViewHolderAdapter : RecyclerView.Adapter<ActorViewHolder>() {

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .circleCrop()

    private var listActors = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun getItemCount(): Int = listActors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(imageOption, listActors[position])

    }

    fun bindActors(newListActors: List<Actor>) {
        listActors = newListActors
        notifyDataSetChanged()
    }
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatar = itemView.findViewById<ImageView>(R.id.img_actor)
    private val name = itemView.findViewById<TextView>(R.id.text_under_img_first_actor)

    fun onBind(options: RequestOptions, actor: Actor) {
        Glide.with(context).load(actor.picture).apply(options).into(avatar)
        name.text = actor.name
    }

}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context