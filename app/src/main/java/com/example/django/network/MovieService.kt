package com.example.django.network

import com.example.django.model.Movie
import com.example.django.network.response.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface MovieService {


    @GET("3/discover/movie?sort_by=popularity.desc")
    fun getDiscoverMovies(): Deferred<List<Movie>>

    @GET("3/movie/{movie_id}")
    fun getMovieById(@QueryMap hashMap: HashMap<String, String> = HashMap()): Deferred<Movie>
}