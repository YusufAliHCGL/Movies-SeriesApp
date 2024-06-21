package com.example.moviesseriesapp.presentation.movie_details

import com.example.moviesseriesapp.domain.model.MovieDetails

data class MovieDetailsState(
    val error: String = "",
    val movieDetails: MovieDetails? = null,
    val isLoading: Boolean = false
)