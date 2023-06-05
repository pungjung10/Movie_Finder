package com.example.cinemo.movie.list

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.cinemo.R
import com.example.cinemo.model.Movie
import com.example.cinemo.model.MovieList

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movieList: MovieList? = null
    private var clickListener: OnItemClickListener? = null


    fun submitList(movies: MovieList) {
        movieList = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList?.movies?.get(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int {
        return movieList?.movies?.size ?: 0
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val titleView: TextView = itemView.findViewById(R.id.title)
        private val dateView: TextView = itemView.findViewById(R.id.date)
        private val genreView: TextView = itemView.findViewById(R.id.genre)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val movie = movieList?.movies?.get(position)
                    movie?.let {
                        clickListener?.onItemClick(it)
                    }
                }
            }
        }

        fun bind(movie: Movie) {
            titleView.text = movie.title_en
            dateView.text = movie.release_date
            genreView.text = movie.genre
            Glide.with(itemView)
                .load(movie.poster_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }
}