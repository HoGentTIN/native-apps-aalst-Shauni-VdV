package com.example.django.ui.fragment.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.django.R
import com.example.django.adapters.DiscoverPagerAdapter
import com.example.django.adapters.FavoritesPagerAdapter
import com.example.django.databinding.FragmentDiscoverBinding
import com.example.django.databinding.FragmentFavoritesBinding
import com.example.django.ui.fragment.discover.DiscoverViewModel
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by lazy {
        ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewPager
        favorites_viewpager.adapter = FavoritesPagerAdapter(childFragmentManager)
        favorites_viewpager_tablayout.setupWithViewPager(favorites_viewpager)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i("Favorites", "Fragment created")

        val binding : FragmentFavoritesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        activity?.setTitle("Favorites")

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this


        // Giving the binding access to the FavoritesViewModel
        binding.viewModel = viewModel

        return binding.root
    }
}