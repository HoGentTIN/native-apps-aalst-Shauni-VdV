package com.example.django.model.repository

import com.example.django.model.Movie
import com.example.django.network.response.MovieListResponse

interface IMovieRepository {

    /**
     *
     * API related functions
     *
     */
    suspend fun getPopularMovies(): MovieListResponse

    suspend fun getLatestMovies(): MovieListResponse

    suspend fun getTopRatedMovies(): MovieListResponse

    suspend fun getFavoriteMovies(): List<Movie>

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

    suspend fun updateDao(movie: Movie)

    suspend fun deleteMovieFromDao(movie: Movie)

    suspend fun deleteMovieFromDaoById(id: String)

    suspend fun deleteAllFromDao()
}
