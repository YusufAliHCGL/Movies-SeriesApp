package com.example.moviesseriesapp.presentation

sealed class Screen(val route: String) {
    data object MoviesScreen: Screen("movies_screen")
    data object MovieDetailsScreen: Screen("movie_detail_screen")
}