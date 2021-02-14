package com.vitaliidiadchenko.mykotkinapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vitaliidiadchenko.mykotkinapplication.R
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

class MovieAdapter(private var onPosterCardClickListener: OnPosterCardClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            onPosterCardClickListener.onClick(movies[position])
        }
    }

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val ageRating = itemView.findViewById<TextView>(R.id.card_age_rating)
    private val poster = itemView.findViewById<ImageView>(R.id.card_poster)
    private val title = itemView.findViewById<TextView>(R.id.card_movie_title)
    private val like = itemView.findViewById<ImageView>(R.id.img_like)
    private val rating = itemView.findViewById<RatingBar>(R.id.card_rating_bar)
    private val review = itemView.findViewById<TextView>(R.id.card_text_review)
    private val tagLine = itemView.findViewById<TextView>(R.id.card_tag_line)
    private val runTime = itemView.findViewById<TextView>(R.id.run_time_of_the_film)

    fun onBind(movie: Movie) {

        Glide.with(context).load(movie.poster).into(poster)
        like.setImageResource(if (movie.like) R.drawable.ic_like else R.drawable.ic_empty_like)
        if (movie.adult) {
            ageRating.text = itemView.resources.getString(R.string.adult_age_rating)
        } else {
            ageRating.visibility = View.INVISIBLE
        } //json doesn't have ageRating
        title.text = movie.title
        rating.rating = (movie.ratings / 2)
        review.text =
            context.resources.getQuantityString(R.plurals.review, 0, 0) //json doesn't have review
        tagLine.text = movie.genres.joinToString(", ")
        runTime.text =
            movie.runtime?.let {
                context.resources.getQuantityString(
                    R.plurals.run_time,
                    it, movie.runtime
                )
            }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnPosterCardClickListener {
    fun onClick(movie: Movie)
}