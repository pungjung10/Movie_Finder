package com.example.cinemo.movie.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemo.R
import com.example.cinemo.databinding.MovieListFragmentBinding
import com.example.cinemo.model.Movie
import com.example.cinemo.model.MovieList
import com.example.cinemo.movie.detail.MovieDetailViewModel
import com.example.cinemo.movie.list.MoviesAdapter

class FavoriteMovieListFragment : Fragment(), MoviesAdapter.OnItemClickListener {

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MoviesAdapter
    private lateinit var binding: MovieListFragmentBinding
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieListFragmentBinding.inflate(inflater, container, false)
        movieRecyclerView = binding.movieList
        movieAdapter = MoviesAdapter()
        movieAdapter.setOnItemClickListener(this)

        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer { data ->

            if(data.isEmpty()){
                binding.apply {
                    empty.isVisible = true
                    movieList.isVisible = false
                    searchView.isVisible = false
                }
            }else{
                val movies = MovieList(
                    data.map {
                        Movie(
                            id = it.movieId,
                            title_en = it.title_en,
                            genre = it.genre,
                            release_date = it.release_date,
                            synopsis_en = it.synopsis_en,
                            poster_url = it.poster_url
                        )
                    }
                )
                movieAdapter.submitList(movies)
                binding.empty.visibility = View.GONE
            }
        })


        movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

        binding.apply {
            title.text = getString(R.string.my_favorite)
            searchView.isVisible = false
        }

        return binding.root
    }

    override fun onItemClick(movie: Movie) {
        val navController = findNavController()
        val action = FavoriteMovieListFragmentDirections.actionFavoriteMovieListFragmentToMovieDetailFragment(movie)
        navController.navigate(action)
    }
}