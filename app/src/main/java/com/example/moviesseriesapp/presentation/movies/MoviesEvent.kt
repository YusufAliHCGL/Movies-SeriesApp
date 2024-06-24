package com.example.moviesseriesapp.presentation.movies

sealed class MoviesEvent {
    data class Search(val search: String): MoviesEvent()
}