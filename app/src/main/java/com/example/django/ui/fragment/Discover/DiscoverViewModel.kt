package com.example.django.ui.fragment.Discover

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

    enum class ApiStatus { LOADING, ERROR, DONE }

    private val _discoverMovies = MutableLiveData<List<Movie>>()
    val discoverMovies: LiveData<List<Movie>>
        get() = _discoverMovies

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    @Inject
    lateinit var movieRepository: IMovieRepository
    @Inject
    lateinit var movieService: MovieService
    init {
        App.appComponent.inject(this)
        getMovies()
    }

    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)


    fun getMovies(){

        coroutineScope.launch{
            var getPropertiesDeferred = movieService.getDiscoverMovies()
            try{
                _status.value = ApiStatus.LOADING
                var result = getPropertiesDeferred.await()
                _discoverMovies.value = result.results
                _status.value = ApiStatus.DONE
            }catch (t: Throwable){
                _status.value = ApiStatus.ERROR
                _discoverMovies.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}