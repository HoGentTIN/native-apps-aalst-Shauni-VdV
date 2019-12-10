package com.example.django.network

import com.example.django.model.Movie
import com.example.django.network.response.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface MovieService {


    @GET("3/discover/movie?api_key=1db8f6ebe89295a86017d0bfe634af7b")
    suspend fun getDiscoverMovies(): MovieListResponse

    @GET("3/movie/{movie_id}")
    fun getMovieById(@QueryMap hashMap: HashMap<String, String> = HashMap()): Movie
}