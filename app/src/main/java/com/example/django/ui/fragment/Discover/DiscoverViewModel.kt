package com.example.django.ui.fragment.Discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.django.App
import com.example.django.model.Movie
import kotlinx.coroutines.launch

class DiscoverViewModel: ViewModel() {
    private val _discoverMovies = MutableLiveData<List<Movie>>()
    val discoverMovies: LiveData<List<Movie>>
        get() = _discoverMovies


    init {
        App.appComponent.inject(this)

    }


}