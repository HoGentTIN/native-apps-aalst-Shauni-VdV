package com.example.django.model.repository


import com.example.django.model.Movie
import com.example.django.network.response.MovieListResponse
import kotlinx.coroutines.Deferred

interface IMovieRepository {

    /**
     *
     * API related functions
     *
     */
    suspend fun getMovieList(): MovieListResponse

    suspend fun insertMovieDatabase(
        list: List<Movie>
    )

    suspend fun updateMovieDatabase(
        movie: Movie
    )

    /**
     *
     * Local Database related functions
     *
     */
    /**
     * local movie db functions
     */

    suspend fun getMovieListFromDao(): List<Movie>?

    suspend fun getMovieFromDao(id: String): Movie?

    suspend fun insertMovieInDao(movie: Movie)

    suspend fun insertMoviesInDao(list: List<Movie>)

    suspend fun updateDao(movie: Movie)

    suspend fun deleteMovieFromDao(movie: Movie)

    suspend fun deleteMovieFromDaoById(id: String)

    suspend fun deleteAllFromDao()

    suspend fun getMoviePageFromDao(pageSize: Int, pageIndex: Int): List<Movie>?

    suspend fun getFavoriteFromDao(pageSize: Int, pageIndex: Int): List<Movie>?
}