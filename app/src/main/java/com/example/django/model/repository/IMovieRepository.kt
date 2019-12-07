package com.example.django.model.repository

import com.example.django.model.Movie

interface IMovieRepository {

    suspend fun loadAllMovies()

    suspend fun addMoviesToDatabase(movies: List<Movie>)

    suspend fun getAll(): List<Movie>
}