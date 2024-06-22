package com.example.moviesseriesapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Favorite(
    val genre: String,
    val imdbID: String,
    val imdbRating: String,
    val poster: String,
    val title: String,
    val type: String,
    val year: String,
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}