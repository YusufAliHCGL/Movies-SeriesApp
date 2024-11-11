package com.example.moviesseriesapp.presentation.favorites

sealed class FavoritesEvent {
    class ChangeFilter(val filterEvent: FilterEvent) : FavoritesEvent()
    class GetFavorites() : FavoritesEvent()
}