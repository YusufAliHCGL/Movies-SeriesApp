package com.example.moviesseriesapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesseriesapp.data.remote.MoviesAPI
import com.example.moviesseriesapp.data.repository.FavoriteRepositoryImpl
import com.example.moviesseriesapp.data.repository.MoviesRepositoryImpl
import com.example.moviesseriesapp.data.room.FavoriteDatabase
import com.example.moviesseriesapp.domain.repository.FavoriteRepository
import com.example.moviesseriesapp.domain.repository.MoviesRepository
import com.example.moviesseriesapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun providesFavoriteDatabase(@ApplicationContext context: Context): FavoriteDatabase =
        Room.databaseBuilder(context = context,
            klass = FavoriteDatabase::class.java,
            name = "FavoriteDatabase")
            .build()

    @Provides
    @Singleton
    fun providesFavoriteRepository(favoriteDatabase: FavoriteDatabase): FavoriteRepository = FavoriteRepositoryImpl(favoriteDatabase = favoriteDatabase)
}