package com.example.moviesseriesapp.domain.repository

import com.example.moviesseriesapp.domain.model.Favorite

interface FavoriteRepository {
    suspend fun getFavoritesFromDatabase(): List<Favorite>

    suspend fun insertToFavoriteDatabase(favorite: Favorite): Long
}