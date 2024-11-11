package com.example.moviesseriesapp.presentation.movies

sealed class MoviesEvent {
    data class Search(val search: String): MoviesEvent()
    data class ChangeFilter(val filterEvent: FilterEvent): MoviesEvent()
    data class ChangeSearchText(val text: String): MoviesEvent()
}