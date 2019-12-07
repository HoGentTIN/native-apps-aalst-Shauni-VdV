package com.example.django.model.repository

import android.content.Context
import com.example.django.model.Movie

class MovieRepository(context : Context) : IMovieRepository {
    override suspend fun getAll(): List<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addMoviesToDatabase(movies: List<Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun loadAllMovies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}