package com.example.django.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class DetailTvShowFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentTvShowDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val tvShow = DetailTvShowFragmentArgs.fromBundle(arguments!!).selectedShow
        val viewModelFactory = DetailTvShowViewModelFactory(tvShow, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(DetailTvShowViewModel::class.java)
        return binding.root
    }
}