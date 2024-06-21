package com.example.moviesseriesapp.data.remote.dto


import com.example.moviesseriesapp.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesDto(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Search>,
    @SerializedName("totalResults")
    val totalResults: String
)

fun MoviesDto.toMovies(): List<Movie> {
    return search.map {movie ->
        Movie(imdbID = movie.imdbID, poster = movie.poster, title = movie.title, type = movie.type, year = movie.year)
    }
}