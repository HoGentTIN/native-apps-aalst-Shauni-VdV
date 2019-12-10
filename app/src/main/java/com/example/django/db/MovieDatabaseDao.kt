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

    /*
    @Query("SELECT * FROM playlist " +
    "WHERE playlist_title LIKE '% :playlistTitle %' " +
    "GROUP BY playlist_title " +
    "ORDER BY playlist_title " +
    "LIMIT :limit")
    List<IPlaylist> searchPlaylists(String playlistTitle, int limit);
     */
    @Query("SELECT * FROM movie_table LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getMoviePage(pageSize: Int, pageIndex: Int): List<Movie>?

    @Query("SELECT * FROM movie_table WHERE isFavorite = 1 LIMIT :pageSize OFFSET ((:pageIndex-1)*:pageSize) ")
    suspend fun getFavorite(pageSize: Int, pageIndex: Int): List<Movie>?
}