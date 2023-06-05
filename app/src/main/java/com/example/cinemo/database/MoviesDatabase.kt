package com.example.cinemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cinemo.data.FavoriteMovieDao
import com.example.cinemo.model.table.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        private const val DB_NAME = "movie_database.db"

        fun getDatabase(context: Context): MoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}