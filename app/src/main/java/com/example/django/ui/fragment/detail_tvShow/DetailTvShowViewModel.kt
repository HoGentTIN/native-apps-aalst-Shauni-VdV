package com.example.django.ui.fragment.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.django.App
import com.example.django.R
import com.example.django.model.Movie
import com.example.django.model.TvShow

class DetailTvShowViewModel(tvShow: TvShow, app: Application) : AndroidViewModel(app) {

    var tvShow = tvShow
    init {
        App.appComponent.inject(this)
    }

    private val _selectedTvShow = MutableLiveData<TvShow>()

    // The external LiveData for the SelectedMovie
    val selectedTvShow: LiveData<TvShow>
        get() = _selectedTvShow

    // Initialize the _selectedMovie MutableLiveData
    init {
        _selectedTvShow.value = tvShow
    }

}