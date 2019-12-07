package com.example.django.ui.fragment.Discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.django.R
import com.example.django.databinding.FragmentDiscoverBinding
import androidx.lifecycle.ViewModelProviders

class DiscoverFragment : Fragment() {


    lateinit var viewModel : DiscoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        activity?.setTitle("Discover")
        val binding: FragmentDiscoverBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_discover, container, false
        )

        viewModel = ViewModelProviders.of(this, DiscoverViewModelFactory()).get(DiscoverViewModel::class.java))
        return binding.root
    }
}