package com.example.cinemo.api

import com.example.cinemo.model.MovieList

interface MoviesApi {

    suspend fun getMovies(): MovieList
}