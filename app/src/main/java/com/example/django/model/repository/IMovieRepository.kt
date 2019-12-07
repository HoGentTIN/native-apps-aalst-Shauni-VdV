package com.example.django.model.repository

import com.example.django.model.Movie

interface IMovieRepository {


    suspend fun getDiscover(): List<Movie>
}