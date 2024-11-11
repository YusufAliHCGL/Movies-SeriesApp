package com.example.moviesseriesapp.presentation.favorites

import com.example.moviesseriesapp.domain.model.Favorite

data class FavoritesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movies: List<Favorite> = emptyList(),
    val filterEvent: FilterEvent = FilterEvent.AllEvent
)