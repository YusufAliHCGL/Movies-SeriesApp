package com.example.moviesseriesapp.presentation.movies

sealed class FilterEvent {
    data object MoviesFilterEvent: FilterEvent()
    data object SeriesFilterEvent: FilterEvent()
    data object AllEvent: FilterEvent()
}