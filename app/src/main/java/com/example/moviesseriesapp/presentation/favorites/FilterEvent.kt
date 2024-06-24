package com.example.moviesseriesapp.presentation.favorites

sealed class FilterEvent {
    data object MoviesFilterEvent: FilterEvent()
    data object SeriesFilterEvent: FilterEvent()
    data object AllEvent: FilterEvent()
}