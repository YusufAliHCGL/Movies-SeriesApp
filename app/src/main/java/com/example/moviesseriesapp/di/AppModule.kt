package com.example.moviesseriesapp.di

import com.example.moviesseriesapp.data.remote.MoviesAPI
import com.example.moviesseriesapp.data.repository.MoviesRepositoryImpl
import com.example.moviesseriesapp.domain.repository.MoviesRepository
import com.example.moviesseriesapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoviesAPI(): MoviesAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MoviesAPI::class.java)

    @Provides
    @Singleton
    fun providesMoviesRepository(api: MoviesAPI): MoviesRepository = MoviesRepositoryImpl(api)
}