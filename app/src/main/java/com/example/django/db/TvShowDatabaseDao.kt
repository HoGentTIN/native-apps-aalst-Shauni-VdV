package com.example.django.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.django.model.TvShow

interface TvShowDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<TvShow>)

    @Query("SELECT * FROM tvshow_table")
    suspend fun getTvShowList(): List<TvShow>?

    @Query("SELECT * FROM tvshow_table WHERE id = :id")
    suspend fun getTvShow(id: String): TvShow?

    @Query("SELECT * FROM tvshow_table WHERE isFavorite")
    suspend fun getFavoriteTvShows() : List<TvShow>

    @Query("UPDATE tvshow_table SET isFavorite = 1 WHERE id = :id")
    suspend fun addTvShowToFavorites(id: String)

    @Query("UPDATE tvshow_table SET isFavorite = 0 WHERE id = :id")
    suspend fun deleteTvShowFromFavorites(id: String)


}