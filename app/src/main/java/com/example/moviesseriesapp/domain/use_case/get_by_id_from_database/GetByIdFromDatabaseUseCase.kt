package com.example.moviesseriesapp.domain.use_case.get_by_id_from_database

import com.example.moviesseriesapp.domain.model.Favorite
import com.example.moviesseriesapp.domain.repository.FavoriteRepository
import com.example.moviesseriesapp.util.Constants.DATA_ERROR
import com.example.moviesseriesapp.util.Resource
import javax.inject.Inject

class GetByIdFromDatabaseUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {
    suspend fun executeGetByIdFromDatabase(imdbId: String): Resource<Favorite> {

        return try {
            val response = favoriteRepository.getDataById(imdbId = imdbId)
            if (response != null) {
                Resource.Success(data = response)
            }else {
                Resource.Error(message = DATA_ERROR)
            }
        }catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "Error")
        }
    }
}