package com.example.django.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.django.R
import com.example.django.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        activity?.setTitle("Discover")
        val binding: FragmentDiscoverBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_discover, container, false
        )
        return binding.root
    }
}