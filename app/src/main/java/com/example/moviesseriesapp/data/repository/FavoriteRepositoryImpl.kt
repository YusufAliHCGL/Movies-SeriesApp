package com.example.moviesseriesapp.data.repository

import com.example.moviesseriesapp.data.room.FavoriteDao
import com.example.moviesseriesapp.data.room.FavoriteDatabase
import com.example.moviesseriesapp.domain.model.Favorite
import com.example.moviesseriesapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(favoriteDatabase: FavoriteDatabase)
    : FavoriteRepository {

    private val dao: FavoriteDao = favoriteDatabase.getDao()
    override suspend fun getFavoritesFromDatabase(): List<Favorite> {
        return dao.getAllFromDatabase()
    }
    override suspend fun insertToFavoriteDatabase(favorite: Favorite): Long {
        return dao.insertToDatabase(favorite = favorite)
    }

    override suspend fun getDataById(imdbId: String): Favorite {
        return dao.getByImdbId(imdbId)
    }

}