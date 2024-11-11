package com.example.moviesseriesapp.presentation.movies

import com.example.moviesseriesapp.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movies: List<Movie> = emptyList(),
    val search: String = "",
    val filterEvent: FilterEvent = FilterEvent.AllEvent
)