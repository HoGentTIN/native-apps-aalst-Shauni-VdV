package com.example.django.ui.fragment.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.django.model.Movie

class DetailMovieViewModelFactory(
    private val movie: Movie,
    private val application: Application
) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
                return DetailMovieViewModel(movie, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
