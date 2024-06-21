package com.example.moviesseriesapp.presentation.movies.views

sealed class MoviesEvent {
    data class Search(val search: String): MoviesEvent()
}