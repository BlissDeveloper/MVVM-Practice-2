package com.example.mvvm_practice_2.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_practice_2.R
import com.example.mvvm_practice_2.model.Movie
import com.example.mvvm_practice_2.network.MovieEndpoints
import kotlinx.android.synthetic.main.movie_layout.view.*

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var mMovies = mutableListOf<Movie>()

    fun setData(movies: List<Movie>) {
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mContext = view.context

        private val ivMoviePoster = view.ivMoviePoster
        private val tvMovieTitle = view.tvMovieTitle

        fun bind(currentMovie: Movie) {
            val imagePath = "${MovieEndpoints.IMAGE_PATH}${currentMovie.poster_path}"
            Glide.with(mContext).load(imagePath)
                .into(ivMoviePoster)
            tvMovieTitle.text = currentMovie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(mMovies[position])
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }
}