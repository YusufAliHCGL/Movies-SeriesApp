package com.example.moviesseriesapp.domain.repository

import com.example.moviesseriesapp.domain.model.Favorite

interface FavoriteRepository {
    suspend fun getFavoritesFromDatabase(): List<Favorite>

    suspend fun insertToFavoriteDatabase(favorite: Favorite): Long

    suspend fun getDataById(imdbId: String): Favorite

    suspend fun deleteFromFavorite(favorite: Favorite): Int
}