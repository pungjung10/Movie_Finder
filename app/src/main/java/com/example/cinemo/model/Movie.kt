package com.example.cinemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MovieList(
    val movies: List<Movie>
)

@Parcelize
data class Movie(
    val id: String,
    val title_en: String,
    val release_date: String,
    val synopsis_en: String,
    val genre: String,
    val poster_url: String
): Parcelable
