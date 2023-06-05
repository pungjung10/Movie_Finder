package com.example.cinemo.repository

import androidx.lifecycle.LiveData
import com.example.cinemo.data.FavoriteMovieDao
import com.example.cinemo.model.table.FavoriteMovie

class FavoriteMovieRepository(private val favoriteMovieDao: FavoriteMovieDao) {
    fun getAllMovies(): LiveData<List<FavoriteMovie>> = favoriteMovieDao.getAllMovies()

    suspend fun insertMovie(movie: FavoriteMovie) {
        favoriteMovieDao.insertMovie(movie)
    }

    fun getFavoriteMovieById(movieId: String): LiveData<FavoriteMovie> {
        return favoriteMovieDao.getFavoriteMovieById(movieId)
    }

    suspend fun deleteMovieById(id: String) {
        favoriteMovieDao.deleteMovieById(id)
    }

}