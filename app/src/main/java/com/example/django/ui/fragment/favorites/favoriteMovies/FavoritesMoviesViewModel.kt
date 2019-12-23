package com.example.django.ui.fragment.favorites.favoriteMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.django.App
import com.example.django.model.Movie
import com.example.django.model.repository.IMovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesMoviesViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
        getFavoriteMovies()
    }

    enum class ApiStatus { LOADING, ERROR, DONE }


    @Inject
    lateinit var movieRepository: IMovieRepository

    private val _favoriteMovies = MutableLiveData<List<Movie>>()
    val favoriteMovies: LiveData<List<Movie>>
        get() = _favoriteMovies

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            var result = movieRepository.getFavoriteMovies()
            _favoriteMovies.value = result
        }
    }

    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }


}