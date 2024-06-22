package com.example.moviesseriesapp.domain.use_case.insert_to_favorite_database

import com.example.moviesseriesapp.domain.model.Favorite
import com.example.moviesseriesapp.domain.repository.FavoriteRepository
import com.example.moviesseriesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertToFavoriteDatabaseUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    fun executeInsertToFavoriteDatabase(favorite: Favorite): Flow<Resource<Long>> = flow{
        emit(Resource.Loading())
        val resource = favoriteRepository.insertToFavoriteDatabase(favorite)
        if(resource != 0L) {
            emit(Resource.Success(data =  resource))
        }else {
            emit(Resource.Error("Error"))
        }
    }

}