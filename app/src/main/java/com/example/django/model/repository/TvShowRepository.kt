package com.example.django.model.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import com.example.django.App
import com.example.django.db.TvShowDatabase
import com.example.django.db.TvShowDatabaseDao
import com.example.django.model.TvShow
import com.example.django.network.TvService
import com.example.django.network.response.MovieListResponse
import com.example.django.network.response.TvListResponse
import javax.inject.Inject

class TvShowRepository(context: Context) : ITvShowRepository {



    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var tvService: TvService


    private val context: Context = context
    private val tvShowDatabase = TvShowDatabase.getInstance(context)
    private val tvShowDao: TvShowDatabaseDao = tvShowDatabase.tvShowDao


    override suspend fun getPopularTvShows(): TvListResponse {
        if (isInternetAvailable(context)) {
            var result = tvService.getPopularShows()
            var showsInDao : List<TvShow> = tvShowDao.getTvShowList()!!

            for(n in result.results!!){
                if(!showsInDao.contains(n)){
                    tvShowDao.insert(n)
                }
            }
            Log.d("DaoTV",  tvShowDao.getTvShowList()?.size.toString())

            return result

        } else {
            Toast.makeText(context, "No Internet Available", Toast.LENGTH_SHORT).show()
            return TvListResponse()
        }
    }

    override suspend fun getLatestTvShows(): TvListResponse {
        if (isInternetAvailable(context)) {
            var result = tvService.getLatestShows()
            var showsInDao : List<TvShow> = tvShowDao.getTvShowList()!!

            for(n in result.results!!){
                if(!showsInDao.contains(n)){
                    tvShowDao.insert(n)
                }
            }
            return result

        } else {
            Toast.makeText(context, "No Internet Available", Toast.LENGTH_SHORT).show()
            return TvListResponse()
        }
    }

    override suspend fun getTopRatedTvShows(): TvListResponse {
        if (isInternetAvailable(context)) {
            var result = tvService.getTopRatedShows()
            var showsInDao : List<TvShow> = tvShowDao.getTvShowList()!!

            for(n in result.results!!){
                if(!showsInDao.contains(n)){
                    tvShowDao.insert(n)
                }
            }
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