package com.example.moviesseriesapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesseriesapp.domain.model.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun getDao(): FavoriteDao

}