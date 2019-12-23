package com.example.django.ui.fragment.favorites.favoriteTvShows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.django.App
import com.example.django.model.TvShow
import com.example.django.model.repository.ITvShowRepository
import com.example.django.model.repository.TvShowRepository
import com.example.django.ui.fragment.favorites.FavoritesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesTvShowsViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
        getFavoriteTvShows()
    }


    @Inject
    lateinit var tvShowRepository: ITvShowRepository

    private val _favoriteShows = MutableLiveData<List<TvShow>>()
    val favoriteShows: LiveData<List<TvShow>>
        get() = _favoriteShows

    private val _navigateToSelectedTvShow = MutableLiveData<TvShow>()
    val navigateToSelectedTvShow: LiveData<TvShow>
        get() = _navigateToSelectedTvShow

    private fun getFavoriteTvShows() {
        viewModelScope.launch {
            var result = tvShowRepository.getFavoriteTvShows()
            _favoriteShows.value = result
        }
    }

    fun displayTvShowDetails(tvShow: TvShow) {
        _navigateToSelectedTvShow.value = tvShow
    }
    fun displayTvShowDetailsComplete() {
        _navigateToSelectedTvShow.value = null
    }

}