package com.example.moviesseriesapp.data.repository

import com.example.moviesseriesapp.data.remote.MoviesAPI
import com.example.moviesseriesapp.data.remote.dto.MovieDetailsDto
import com.example.moviesseriesapp.data.remote.dto.MoviesDto
import com.example.moviesseriesapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesAPI: MoviesAPI)
    : MoviesRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return moviesAPI.getMovies(search = search)
    }

    override suspend fun getMovieDetails(imdbId: String): MovieDetailsDto {
        return moviesAPI.getMovieDetails(imdbId = imdbId)
    }
}