package com.example.django.ui.fragment.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.django.App
import com.example.django.model.Movie
import com.example.django.model.Person
import com.example.django.model.TvShow
import com.example.django.model.helpers.Searchable
import com.example.django.network.MovieService
import com.example.django.network.response.SearchResponse
import io.reactivex.Observable
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

class SearchViewModel : BaseSearchViewModel<Searchable>() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var movieService: MovieService

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private val _navigateToSelectedTvShow = MutableLiveData<TvShow>()
    val navigateToSelectedTvShow: LiveData<TvShow>
        get() = _navigateToSelectedTvShow

    private val _navigateToSelectedPerson = MutableLiveData<Person>()
    val navigateToSelectedPerson: LiveData<Person>
        get() = _navigateToSelectedPerson

    var query = PublishProcessor.create<String>()
    fun getSearchResults(query: String, page: Int): Observable<SearchResponse> {
        val results = movieService.getSearchResults(query, page)
        Log.d("getSearchResults", results.toString())
        return results
    }

    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }

    fun displayTvShowDetails(tvShow: TvShow) {
        _navigateToSelectedTvShow.value = tvShow
    }
    fun displayTvShowDetailsComplete() {
        _navigateToSelectedTvShow.value = null
    }

    fun displayPersonDetails(person: Person) {
        _navigateToSelectedPerson.value = person
    }
    fun displayPersonDetailsComplete() {
        _navigateToSelectedPerson.value = null
    }
}
