package com.example.django.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.django.databinding.FragmentPersonDetailBinding


class DetailPersonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application

        val binding = FragmentPersonDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val person = DetailPersonFragmentArgs.fromBundle(arguments!!).selectedPerson

        val viewModelFactory = DetailPersonViewModelFactory(person, application)

        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(DetailPersonViewModel::class.java)
        return binding.root
    }


}