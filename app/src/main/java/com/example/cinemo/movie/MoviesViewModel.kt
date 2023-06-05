package com.example.cinemo.movie

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemo.model.MovieList
import com.example.cinemo.repository.MovieRepository
import com.example.cinemo.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel() : ViewModel() {

    private val _moviesLiveData = MutableLiveData<MovieList>()
    val moviesLiveData: LiveData<MovieList> = _moviesLiveData

    private val movieRepository = MovieRepository(RetrofitInstance.service)


    fun getMovies() {
        viewModelScope.launch {
            try {
                val movies = withContext(Dispatchers.IO) {
                    movieRepository.getMovies()
                }
                _moviesLiveData.value = movies
            } catch (e: Exception) {
                Log.e(TAG, "Error retrieving movies", e)
            }
        }
    }


}