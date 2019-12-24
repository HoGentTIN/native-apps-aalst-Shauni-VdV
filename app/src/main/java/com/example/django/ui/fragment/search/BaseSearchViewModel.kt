package com.example.django.ui.fragment.search

import androidx.lifecycle.ViewModel

abstract class BaseSearchViewModel<T> : ViewModel() {

    var pageNumber: Int = 0
    val list = mutableListOf<T>()
}