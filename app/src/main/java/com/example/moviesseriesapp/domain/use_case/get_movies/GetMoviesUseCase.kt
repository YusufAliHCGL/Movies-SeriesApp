package com.example.moviesseriesapp.domain.use_case.get_movies

import com.example.moviesseriesapp.data.remote.dto.toMovies
import com.example.moviesseriesapp.domain.model.Movie
import com.example.moviesseriesapp.domain.repository.MoviesRepository
import com.example.moviesseriesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun executeGetMovies(search: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val moviesDto = moviesRepository.getMovies(search)
            if (moviesDto.response == "True") {
                emit(Resource.Success(data = moviesDto.toMovies()))
            }else {
                emit(Resource.Error(message = "No Result Found!"))
            }
        }catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }
    }

}