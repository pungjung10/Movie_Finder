package com.example.cinemo.movie.detail

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.cinemo.databinding.MovieDetailFragmentBinding
import com.example.cinemo.model.Movie
import com.example.cinemo.model.table.FavoriteMovie

class MovieDetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding
    private lateinit var viewModel: MovieDetailViewModel
    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        movie = args.movie

        setContent()
        favoriteMovie()
        observer()

        return binding.root
    }

    private fun observer(){
        viewModel.getFavoriteMovieById(movie.id).observe(viewLifecycleOwner) { favoriteMovie ->
            binding.favoriteButton.isChecked = favoriteMovie != null
        }
    }

    private fun favoriteMovie(){
        binding.favoriteButton.apply {
            setOnClickListener() {
                if (this.isChecked) {
                    val data = FavoriteMovie(
                        id = 0,
                        movieId = movie.id,
                        title_en = movie.title_en,
                        release_date = movie.release_date,
                        synopsis_en = movie.synopsis_en,
                        genre = movie.genre,
                        poster_url = movie.poster_url
                    )
                    viewModel.insertMovie(data)
                    Toast.makeText(requireContext(), "Movie saved", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.deleteMovieById(movie.id)
                    Toast.makeText(requireContext(), "Movie removed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setContent(){
        binding.apply {
            title.text = movie.title_en
            genre.text = movie.genre
            synopsis.text = movie.synopsis_en
            Glide.with(this@MovieDetailFragment)
                .load(movie.poster_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }
}