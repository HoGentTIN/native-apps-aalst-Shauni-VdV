package com.example.django.ui.fragment.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.django.model.Person

class DetailPersonViewModelFactory(
    private val person: Person,
    private val application: Application
) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailPersonViewModel::class.java)) {
                return DetailPersonViewModel(person, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
