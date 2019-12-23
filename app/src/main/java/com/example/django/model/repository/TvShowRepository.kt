package com.example.django.model.repository

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.example.django.App
import com.example.django.db.TvShowDatabase
import com.example.django.db.TvShowDatabaseDao
import com.example.django.model.TvShow
import com.example.django.network.TvService
import com.example.django.network.response.TvListResponse
import javax.inject.Inject

class TvShowRepository(context: Context) : ITvShowRepository {

    @Inject
    lateinit var tvService: TvService

    init {
        App.appComponent.inject(this)
    }

    private val context: Context = context
    private val tvShowDatabase = TvShowDatabase.getInstance(context)
    private val tvShowDao: TvShowDatabaseDao = tvShowDatabase.tvShowDao


    override suspend fun getPopularTvShows(): TvListResponse {
        if (isInternetAvailable(context)) {
            var result = tvService.getPopularShows()
            insertTvShowDatabase(result.results!!)
            return result

        } else {
            Toast.makeText(context, "No Internet Available", Toast.LENGTH_SHORT).show()
            return TvListResponse()
        }
    }

    override suspend fun getLatestTvShows(): TvListResponse {
        if (isInternetAvailable(context)) {
            var result = tvService.getLatestShows()
            insertTvShowDatabase(result.results!!)
            return result

        } else {
            Toast.makeText(context, "No Internet Available", Toast.LENGTH_SHORT).show()
            return TvListResponse()
        }
    }

    override suspend fun getTopRatedTvShows(): TvListResponse {
        if (isInternetAvailable(context)) {
            var result = tvService.getTopRatedShows()
            insertTvShowDatabase(result.results!!)
            return result

        } else {
            Toast.makeText(context, "No Internet Available", Toast.LENGTH_SHORT).show()
            return TvListResponse()
        }
    }

    override suspend fun getFavoriteTvShows(): List<TvShow> {
        return tvShowDao.getFavoriteTvShows()
    }

    override suspend fun insertTvShowDatabase(list: List<TvShow>) {
            tvShowDao.insert(list)

    }

    internal fun isInternetAvailable(context: Context): Boolean {
        val mConMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return (mConMgr.activeNetworkInfo != null
                && mConMgr.activeNetworkInfo!!.isAvailable
                && mConMgr.activeNetworkInfo!!.isConnected)
    }
}