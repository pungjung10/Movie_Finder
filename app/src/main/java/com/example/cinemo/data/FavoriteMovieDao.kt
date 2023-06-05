package com.example.cinemo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinemo.model.table.FavoriteMovie

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: FavoriteMovie)

    @Delete
    suspend fun deleteMovie(movie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movies ORDER BY id DESC")
    fun getAllMovies(): LiveData<List<FavoriteMovie>>

    @Query("SELECT * FROM favorite_movies WHERE movieId = :movieId")
    fun getFavoriteMovieById(movieId: String): LiveData<FavoriteMovie>

    @Query("DELETE FROM favorite_movies WHERE movieId = :movieId")
    suspend fun deleteMovieById(movieId: String)
}