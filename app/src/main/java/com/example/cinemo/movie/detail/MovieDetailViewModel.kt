package com.example.cinemo.movie.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cinemo.database.MoviesDatabase
import com.example.cinemo.model.table.FavoriteMovie
import com.example.cinemo.repository.FavoriteMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FavoriteMovieRepository
    private val allMovies: LiveData<List<FavoriteMovie>>

    init {
        val dao = MoviesDatabase.getDatabase(application).favoriteMovieDao()
        repository = FavoriteMovieRepository(dao)
        allMovies = repository.getAllMovies()
    }

    fun insertMovie(movie: FavoriteMovie) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertMovie(movie) }
    }


    fun getAllMovies(): LiveData<List<FavoriteMovie>> {
        return allMovies
    }

    fun getFavoriteMovieById(movieId: String): LiveData<FavoriteMovie> {
        return repository.getFavoriteMovieById(movieId)
    }

    fun deleteMovieById(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteMovieById(movieId) }
    }

}

