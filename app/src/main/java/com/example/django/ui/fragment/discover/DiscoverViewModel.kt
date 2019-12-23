package com.example.django.ui.fragment.discover

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.django.App
import com.example.django.model.Movie
import com.example.django.model.TvShow
import com.example.django.model.repository.IMovieRepository
import com.example.django.network.MovieService
import com.example.django.network.TvService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


class DiscoverViewModel: ViewModel() {
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