package com.example.django.network

import com.example.django.model.Movie
import com.example.django.network.response.MovieListResponse
import com.example.django.network.response.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieService {

    @GET("3/discover/movie")
    suspend fun getDiscoverMovies(): MovieListResponse

    @GET("3/movie/now_playing")
    suspend fun getLatestMovies(): MovieListResponse

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(): MovieListResponse

    @GET("3/movie/{movie_id}")
    fun getMovieById(@QueryMap hashMap: HashMap<String, String> = HashMap()): Movie

    @GET("3/search/multi")
    fun getSearchResults(@Query("query") query: String, @Query("page") page: Int): Observable<SearchResponse>
}
