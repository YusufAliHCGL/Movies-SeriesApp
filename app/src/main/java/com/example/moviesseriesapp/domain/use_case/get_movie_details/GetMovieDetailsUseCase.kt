package com.example.moviesseriesapp.domain.use_case.get_movie_details

import com.example.moviesseriesapp.data.remote.dto.toMovieDetails
import com.example.moviesseriesapp.domain.model.MovieDetails
import com.example.moviesseriesapp.domain.repository.MoviesRepository
import com.example.moviesseriesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    fun executeGetMovieDetails(imdbId: String): Flow<Resource<MovieDetails>> = flow{
        try {
            emit(Resource.Loading())
            val movieDetailsDto = moviesRepository.getMovieDetails(imdbId = imdbId)
            if (movieDetailsDto.response == "True") {
                emit(Resource.Success(data = movieDetailsDto.toMovieDetails()))
            }else {
                emit(Resource.Error(message = "No Result Found!"))
            }
        }catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }
    }
}