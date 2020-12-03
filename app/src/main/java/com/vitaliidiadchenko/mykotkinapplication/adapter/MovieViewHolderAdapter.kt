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

class MovieViewHolderAdapter : RecyclerView.Adapter<MovieViewHolderAdapter.MovieViewHolder>() {

    private val movies = arrayListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

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

        fun bindMovies(movie: Movie) {

            Glide.with(context).load(movie.poster).into(poster)

            if (movie.like) {
                Glide.with(context).load(R.drawable.img_like).into(like)
            } else {
                Glide.with(context).load(R.drawable.img_empty_like).into(like)
            }

            ageRating.text = movie.ageRating
            title.text = movie.title
            rating.rating = movie.rating.toFloat()
            review.text = movie.review
            tagLine.text = movie.tagLine
            runTime.text = movie.runTime
        }
    }

}

interface OnPosterCardClickListener {
    fun onClick(movie: Movie)
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context