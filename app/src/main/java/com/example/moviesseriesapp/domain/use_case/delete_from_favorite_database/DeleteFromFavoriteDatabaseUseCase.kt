package com.example.moviesseriesapp.domain.use_case.delete_from_favorite_database

import com.example.moviesseriesapp.domain.model.Favorite
import com.example.moviesseriesapp.domain.repository.FavoriteRepository
import com.example.moviesseriesapp.util.Constants.DATA_ERROR
import com.example.moviesseriesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFromFavoriteDatabaseUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {
    fun executeDeleteFromDatabase(favorite: Favorite): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val response = favoriteRepository.deleteFromFavorite(favorite = favorite)
            if (response != 0) {
                emit(Resource.Success(data = response))
            }else {
                emit(Resource.Error(message = DATA_ERROR))
            }
        }catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }

    }
}