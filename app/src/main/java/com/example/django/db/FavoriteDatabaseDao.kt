package com.example.django.db

import androidx.room.Dao
import androidx.room.Query
import com.example.django.model.Movie
import com.example.django.model.TvShow

@Dao
interface FavoriteDatabaseDao {

    @Query("UPDATE movie_table SET isFavorite = 1 WHERE id = :id")
    fun addMovieToFavorites(id: String)

    @Query("UPDATE movie_table SET isFavorite = 0 WHERE id = :id")
    fun deleteMovieFromFavorites(id: String)

    @Query("SELECT * FROM movie_table WHERE isFavorite = 1")
    suspend fun getFavoriteMovies() : List<Movie>


    @Query("SELECT * FROM tvshow_table WHERE isFavorite")
    suspend fun getFavoriteTvShows() : List<TvShow>

    @Query("UPDATE tvshow_table SET isFavorite = 1 WHERE id = :id")
    suspend fun addTvShowToFavorites(id: String)

    @Query("UPDATE tvshow_table SET isFavorite = 0 WHERE id = :id")
    suspend fun deleteTvShowFromFavorites(id: String)

}