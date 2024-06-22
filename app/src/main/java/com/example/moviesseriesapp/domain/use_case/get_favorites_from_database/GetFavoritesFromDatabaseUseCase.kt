package com.example.moviesseriesapp.domain.use_case.get_favorites_from_database

import com.example.moviesseriesapp.domain.model.Favorite
import com.example.moviesseriesapp.domain.repository.FavoriteRepository
import com.example.moviesseriesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesFromDatabaseUseCase @Inject constructor(private val favoritesRepository: FavoriteRepository) {
    fun executeGetFavoritesFromDatabase(): Flow<Resource<List<Favorite>>> = flow {
        try {
            emit(Resource.Loading())
            val response = favoritesRepository.getFavoritesFromDatabase()
            if (response.isNotEmpty()) {
                emit(Resource.Success(data = response))
            }else {
                emit(Resource.Error(message = "Error"))
            }
        }catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error", data = null))
        }
    }
}