package com.example.django.ui.fragment.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.django.App
import com.example.django.model.Movie
import com.example.django.model.TvShow

class DiscoverViewModel : ViewModel() {
    init {
        App.appComponent.inject(this)
    }

    enum class ApiStatus { LOADING, ERROR, DONE }

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private val _navigateToSelectedTvShow = MutableLiveData<TvShow>()
    val navigateToSelectedTvShow: LiveData<TvShow>
        get() = _navigateToSelectedTvShow
}
