package com.example.moviesseriesapp.domain.repository

import com.example.moviesseriesapp.data.remote.dto.MovieDetailsDto
import com.example.moviesseriesapp.data.remote.dto.MoviesDto

interface MoviesRepository {
    suspend fun getMovies(search: String): MoviesDto

    suspend fun getMovieDetails(imdbId: String): MovieDetailsDto
}