package com.example.django.model.repository

import android.content.Context
import android.util.Log
import com.example.django.App
import com.example.django.db.MovieDatabase
import com.example.django.db.MovieDatabaseDao
import com.example.django.model.Movie
import com.example.django.network.MovieService
import com.example.django.network.response.MovieListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.Deferred


class MovieRepository(context : Context) : IMovieRepository {

    @Inject
    lateinit var movieService : MovieService

    init{
        App.appComponent.inject(this)
    }

    private val movieDatabase = MovieDatabase.getInstance(context)
    private val movieDao: MovieDatabaseDao = movieDatabase.movieDao

    override suspend fun getMovieList(): MovieListResponse{
        return movieService.getDiscoverMovies()
    }

    override suspend fun insertMovieDatabase(list: List<Movie>) {
        movieDao.insert(list)
    }

    override suspend fun updateMovieDatabase(movie: Movie) {
        movieDao.update(movie)
    }

    override suspend fun getMovieListFromDao(): List<Movie>? {
        return movieDao.getMovieList()
    }

    override suspend fun getMovieFromDao(id: String): Movie? {
        return movieDao.getMovie(id)
    }

    override suspend fun insertMovieInDao(movie: Movie) {
        return movieDao.insert(movie)
    }

    override suspend fun insertMoviesInDao(list: List<Movie>) {
        return movieDao.insert(list)
    }

    override suspend fun updateDao(movie: Movie) {
        return movieDao.update(movie)
    }

    override suspend fun deleteMovieFromDao(movie: Movie) {
        return movieDao.deleteMovie(movie)
    }

    override suspend fun deleteMovieFromDaoById(id: String) {
        return movieDao.deleteMovie(id)
    }

    override suspend fun deleteAllFromDao() {
        return movieDao.deleteAll()
    }

    override suspend fun getMoviePageFromDao(pageSize: Int, pageIndex: Int): List<Movie>? {
        return movieDao.getMoviePage(pageSize, pageIndex)
    }

    override suspend fun getFavoriteFromDao(pageSize: Int, pageIndex: Int): List<Movie>? {
        return movieDao.getFavorite(pageSize = pageSize, pageIndex = pageIndex)
    }

}