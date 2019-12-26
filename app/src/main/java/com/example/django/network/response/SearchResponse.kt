package com.example.django.network.response

import com.example.django.model.helpers.Searchable
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<Searchable>? = null
)
