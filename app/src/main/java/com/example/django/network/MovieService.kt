package com.example.django.network

import com.example.django.model.Movie
import com.example.django.network.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieService {


    @GET("3/discover/movie")
    suspend fun getDiscoverMovies(@QueryMap hashMap: HashMap<String, String> = HashMap()): MovieListResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieById(@QueryMap hashMap: HashMap<String, String> = HashMap()): Movie
}