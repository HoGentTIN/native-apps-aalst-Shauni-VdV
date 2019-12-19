package com.example.django.network

import com.example.django.network.response.TvListResponse
import retrofit2.http.GET

interface TvService {

    @GET("3/tv/popular")
    suspend fun getPopularShows(): TvListResponse

    @GET("3/tv/latest")
    suspend fun getLatestShows(): TvListResponse

    @GET("3//tv/top_rated")
    suspend fun getTopRatedShows(): TvListResponse
}