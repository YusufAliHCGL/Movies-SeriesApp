package com.example.moviesseriesapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesseriesapp.domain.model.Favorite


@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertToDatabase(favorite: Favorite): Long

    @Query("SELECT * FROM Favorite")
    suspend fun getAllFromDatabase(): List<Favorite>

}