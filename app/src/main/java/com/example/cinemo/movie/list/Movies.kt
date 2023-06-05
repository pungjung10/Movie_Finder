package com.example.cinemo.movie.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemo.R
import com.example.cinemo.databinding.MovieListFragmentBinding
import com.example.cinemo.model.Movie
import com.example.cinemo.model.MovieList
import com.example.cinemo.movie.MoviesViewModel

open class Movies : Fragment(), MoviesAdapter.OnItemClickListener, SearchView.OnQueryTextListener {

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MoviesAdapter
    private lateinit var binding: MovieListFragmentBinding
    private lateinit var viewModel: MoviesViewModel

    private lateinit var movieList: List<Movie>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieListFragmentBinding.inflate(inflater, container, false)
        movieRecyclerView = binding.movieList
        movieAdapter = MoviesAdapter()
        movieAdapter.setOnItemClickListener(this)

        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)


        movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

        viewModel.getMovies()
        observer()

        binding.apply {
            title.text = getString(R.string.movie_finder)
            searchView.setOnQueryTextListener(this@Movies)
            searchView.queryHint = getString(R.string.search_movie)
            movies.setOnClickListener{
                hideKeyboard()
            }
            movieList.setOnClickListener{
                Log.d("testClick","45454545454")
                hideKeyboard()
            }
        }
        return binding.root
    }

    private fun hideKeyboard(){
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.movieList.windowToken, 0)
    }

    private fun observer() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner) { movies ->
            movieList = movies.movies
            movieAdapter.submitList(movies)
        }
    }

    override fun onItemClick(movie: Movie) {
        val navController = findNavController()
        val action = MoviesDirections.actionMoviesToMovieDetailFragment(movie)
        navController.navigate(action)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        performSearch(newText)
        return true
    }

    private fun performSearch(query: String?) {
        val filteredMovies = movieList.filter { movie ->
            movie.title_en.contains(query.orEmpty(), ignoreCase = true) ||
                    movie.synopsis_en.contains(query.orEmpty(), ignoreCase = true) ||
                    movie.release_date.contains(query.orEmpty(), ignoreCase = true) ||
                    movie.genre.contains(query.orEmpty(), ignoreCase = true)
        }
        val movies = MovieList(filteredMovies)

        binding.empty.isVisible = movies.movies.isEmpty()

        movieAdapter.submitList(movies)
    }
}