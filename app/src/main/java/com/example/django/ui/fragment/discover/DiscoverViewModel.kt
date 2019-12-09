package com.example.django.ui.fragment.discover

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.django.App
import com.example.django.model.Movie
import com.example.django.model.repository.IMovieRepository
import com.example.django.network.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class DiscoverViewModel: ViewModel() {



    init {
        App.appComponent.inject(this)
        getMovies()
    }

    enum class ApiStatus { LOADING, ERROR, DONE }

    @Inject
    lateinit var movieRepository: IMovieRepository
    @Inject
    lateinit var movieService: MovieService



    private val _discoverMovies = MutableLiveData<List<Movie>>()
    val discoverMovies: LiveData<List<Movie>>
        get() = _discoverMovies

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getMovies(){

       viewModelScope.launch {
           var any = movieService.getDiscoverMovies()
           Log.i("response", any.toString())
           Log.i("list", any.results.toString())
           _discoverMovies.value = any.results

       }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }
}