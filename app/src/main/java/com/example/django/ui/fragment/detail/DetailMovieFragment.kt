package com.example.django.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.django.databinding.FragmentMovieDetailBinding

class DetailMovieFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentMovieDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val movie = DetailMovieFragmentArgs.fromBundle(arguments!!).selectedMovie
        val viewModelFactory = DetailMovieViewModelFactory(movie, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(DetailMovieViewModel::class.java)
        return binding.root
    }
}