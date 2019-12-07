package com.example.django.model.repository

import android.content.Context
import com.example.django.App
import com.example.django.model.Movie
import com.example.django.network.MovieService
import javax.inject.Inject

class MovieRepository(context : Context) : IMovieRepository {


    @Inject
    lateinit var movieService : MovieService

    init{
        App.appComponent.inject(this)
    }


    override suspend fun getDiscover(): List<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}