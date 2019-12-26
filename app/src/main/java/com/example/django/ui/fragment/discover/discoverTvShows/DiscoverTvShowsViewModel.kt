package com.example.django.ui.fragment.discover.discoverTvShows

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.django.App
import com.example.django.model.TvShow
import com.example.django.model.repository.ITvShowRepository
import com.example.django.network.TvService
import com.example.django.ui.fragment.discover.DiscoverViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverTvShowsViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
        getPopularTvShows()
        getTopRatedTvShows()
        getLatestTvShows()

    }

    enum class ApiStatus { LOADING, ERROR, DONE }

    @Inject
    lateinit var tvService: TvService
    @Inject
    lateinit var tvShowRepository: ITvShowRepository

    private val _popularTvShows = MutableLiveData<List<TvShow>>()
    val popularTvShows: LiveData<List<TvShow>>
        get() = _popularTvShows

    private val _topRatedTvShows = MutableLiveData<List<TvShow>>()
    val topRatedTvShows: LiveData<List<TvShow>>
        get() = _topRatedTvShows

    private val _latestTvShows = MutableLiveData<List<TvShow>>()
    val latestTvShows: LiveData<List<TvShow>>
        get() = _latestTvShows



    private val _status = MutableLiveData<DiscoverViewModel.ApiStatus>()
    val status: LiveData<DiscoverViewModel.ApiStatus>
        get() = _status

    private val _navigateToSelectedTvShow = MutableLiveData<TvShow>()
    val navigateToSelectedTvShow: LiveData<TvShow>
        get() = _navigateToSelectedTvShow

    private fun getPopularTvShows(){

        Log.d("ViewModel", "GetPopularTvShows called")
        viewModelScope.launch {
            var any = tvShowRepository.getPopularTvShows()
            _popularTvShows.value = any.results
        }
    }

    private fun getTopRatedTvShows(){

        Log.d("ViewModel", "GetTopRatedTvShows called")
        viewModelScope.launch {
            var any = tvShowRepository.getTopRatedTvShows()
            _topRatedTvShows.value = any.results
        }
    }

    private fun getLatestTvShows(){

        Log.d("ViewModel", "GetTopRatedTvShows called")
        viewModelScope.launch {
            var any = tvShowRepository.getLatestTvShows()
            _latestTvShows.value = any.results
        }
    }




    fun displayTvShowDetails(tvShow: TvShow) {
        _navigateToSelectedTvShow.value = tvShow
    }
    fun displayTvShowDetailsComplete() {
        _navigateToSelectedTvShow.value = null
    }
}