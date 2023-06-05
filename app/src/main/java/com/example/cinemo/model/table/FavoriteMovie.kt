package com.example.cinemo.model.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    val movieId: String,
    val title_en: String,
    val release_date: String,
    val synopsis_en: String,
    val genre: String,
    val poster_url: String
)