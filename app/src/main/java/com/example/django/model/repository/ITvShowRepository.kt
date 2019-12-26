package com.example.django.model.repository

import com.example.django.model.TvShow
import com.example.django.network.response.TvListResponse

interface ITvShowRepository {

    /**
     *
     * API related functions
     *
     */
    suspend fun getPopularTvShows(): TvListResponse

    suspend fun getLatestTvShows(): TvListResponse

    suspend fun getTopRatedTvShows(): TvListResponse

    suspend fun getFavoriteTvShows(): List<TvShow>

    suspend fun insertTvShowDatabase(list: List<TvShow>)
}
