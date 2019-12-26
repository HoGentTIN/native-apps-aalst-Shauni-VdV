package com.example.django.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.django.model.Movie

@Dao
interface MovieDatabaseDao {

    @Query("SELECT * FROM movie_table")
    suspend fun getMovieList(): List<Movie>?

    @Query("SELECT * FROM movie_table WHERE id = :id")
    suspend fun getMovie(id: String): Movie?

    @Query("SELECT * FROM movie_table WHERE isFavorite = 1")
    suspend fun getFavoriteMovies(): List<Movie>

    @Query("UPDATE movie_table SET isFavorite = 1 WHERE id = :id")
    fun addMovieToFavorites(id: String)

    @Query("UPDATE movie_table SET isFavorite = 0 WHERE id = :id")
    fun deleteMovieFromFavorites(id: String)

    @Insert(onConflict = IGNORE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = IGNORE)
    suspend fun insert(list: List<Movie>)

    @Insert(onConflict = REPLACE)
    suspend fun update(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie_table WHERE id = :id")
    suspend fun deleteMovie(id: String)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()
   }
