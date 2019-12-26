package com.example.django.network.response

import com.google.gson.annotations.SerializedName

open class ListResponse<Item>(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("total_results") val totalResults: Int? = null,
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("results") val results: ArrayList<Item>? = null
) : Response()
