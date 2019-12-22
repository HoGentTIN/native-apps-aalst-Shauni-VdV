package com.example.django.ui.fragment.discover.discoverMovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.django.App
import com.example.django.model.Movie
import com.example.django.model.repository.IMovieRepository
import com.example.django.network.MovieService
import com.example.django.ui.fragment.discover.DiscoverViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverMoviesViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
        Log.d("ViewModel", "passed init before getMovies()")

        getPopularMovies()
        Log.d("ViewModel", "passed init after getPopularMovies()")

        getLatestMovies()
        Log.d("ViewModel", "passed init after getLatestMovies()")

        getTopRatedMovies()
        Log.d("ViewModel", "passed init after getTopRatedMovies()")

    }

    enum class ApiStatus { LOADING, ERROR, DONE }

    @Inject
    lateinit var movieRepository: IMovieRepository
    @Inject
    lateinit var movieService: MovieService

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies

    private val _latestMovies = MutableLiveData<List<Movie>>()
    val latestMovies: LiveData<List<Movie>>
        get() = _latestMovies

    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>>
        get() = _topRatedMovies

    private val _status = MutableLiveData<DiscoverViewModel.ApiStatus>()
    val status: LiveData<DiscoverViewModel.ApiStatus>
        get() = _status


    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private fun getPopularMovies(){

        Log.d("ViewModel", "GetPopularMovies called")
        viewModelScope.launch {
            var any = movieRepository.getPopularMovies()
            Log.i("response", any.toString())
            Log.i("list", any.results.toString())
            _popularMovies.value = any.results
        }
    }

    private fun getLatestMovies(){

        Log.d("ViewModel", "GetPopularMovies called")
        viewModelScope.launch {
            var any = movieRepository.getLatestMovies()
            Log.i("response", any.toString())
            Log.i("list", any.results.toString())
            _latestMovies.value = any.results
        }
    }

    private fun getTopRatedMovies(){

        Log.d("ViewModel", "GetTopRatedMovies called")
        viewModelScope.launch {
            var any = movieRepository.getTopRatedMovies()
            Log.i("response", any.toString())
            Log.i("list", any.results.toString())
            _topRatedMovies.value = any.results
        }
    }

    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }

}