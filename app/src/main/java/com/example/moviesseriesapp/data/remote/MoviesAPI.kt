package com.example.moviesseriesapp.data.remote

import com.example.moviesseriesapp.data.remote.dto.MovieDetailsDto
import com.example.moviesseriesapp.data.remote.dto.MoviesDto
import com.example.moviesseriesapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET(".")
    suspend fun getMovies(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("s") search: String
    ): MoviesDto

    @GET(".")
    suspend fun getMovieDetails(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("i") imdbId: String
    ): MovieDetailsDto

}