package com.example.cinemo.repository

import com.example.cinemo.api.MoviesApi
import com.example.cinemo.model.MovieList

class MovieRepository(private val moviesApi: MoviesApi) {

     suspend fun getMovies(): MovieList {
        return moviesApi.getMovies()
    }
}