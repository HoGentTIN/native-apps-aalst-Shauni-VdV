package com.example.django.ui.fragment.search

interface SearchInterface {

    fun search(query: String)

    companion object {
        const val QUERY = "query"
    }
}
