package com.example.django.ui.fragment.search

import com.example.django.App
import com.example.django.network.MovieService
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

class SearchViewModel {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    private lateinit var movieService: MovieService

    var query = PublishProcessor.create<String>()
    fun getSearchResults(query: String, page: Int) = movieService.getSearchResults(query, page)
}