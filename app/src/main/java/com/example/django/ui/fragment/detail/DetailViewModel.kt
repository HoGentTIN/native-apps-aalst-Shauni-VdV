package com.example.django.ui.fragment.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.django.App
import com.example.django.R
import com.example.django.model.Movie

class DetailViewModel(movie: Movie, app: Application) : AndroidViewModel(app) {

    init {
        App.appComponent.inject(this)
    }

    private val _selectedMovie = MutableLiveData<Movie>()

    // The external LiveData for the SelectedMovie
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    // Initialize the _selectedMovie MutableLiveData
    init {
        _selectedMovie.value = movie
    }

}