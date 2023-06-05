package com.example.cinemo.retrofit

import com.example.cinemo.api.MoviesApi
import com.example.cinemo.model.MovieList
import retrofit2.http.GET

interface RetrofitApi: MoviesApi {

    @GET("/apis/get_movie_avaiable")
    override suspend fun getMovies(): MovieList
}