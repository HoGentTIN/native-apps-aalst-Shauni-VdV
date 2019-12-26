package com.example.django.ui.fragment.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.django.App
import com.example.django.model.Person

class DetailPersonViewModel(person: Person, app: Application) : AndroidViewModel(app) {

    init {
        App.appComponent.inject(this)
    }

    private val _selectedPerson = MutableLiveData<Person>()

    // The external LiveData for the SelectedMovie
    val selectedPerson: LiveData<Person>
        get() = _selectedPerson

    // Initialize the _selectedMovie MutableLiveData
    init {
        _selectedPerson.value = person
    }
}
