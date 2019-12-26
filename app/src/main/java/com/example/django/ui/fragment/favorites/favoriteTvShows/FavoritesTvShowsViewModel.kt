package com.example.django.ui.fragment.favorites.favoriteTvShows

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.django.App
import com.example.django.model.TvShow
import com.example.django.model.repository.ITvShowRepository
import javax.inject.Inject
import kotlinx.coroutines.launch

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
            Log.d("FavoriteShows", result.size.toString())
        }
    }

    fun displayTvShowDetails(tvShow: TvShow) {
        _navigateToSelectedTvShow.value = tvShow
    }
    fun displayTvShowDetailsComplete() {
        _navigateToSelectedTvShow.value = null
    }
}
