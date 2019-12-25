package com.example.django.ui.fragment.search

import android.util.Log
import com.example.django.App
import com.example.django.model.helpers.Searchable
import com.example.django.network.MovieService
import com.example.django.network.response.SearchResponse
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

class SearchViewModel : BaseSearchViewModel<Searchable>() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var movieService: MovieService

    var query = PublishProcessor.create<String>()
    fun getSearchResults(query: String, page: Int) : Single<SearchResponse> {
        val results = movieService.getSearchResults(query, page)
        Log.d("getSearchResults", results.toString())
        return results
    }

}