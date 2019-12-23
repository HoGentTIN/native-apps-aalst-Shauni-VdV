package com.example.django.ui.fragment.favorites.favoriteTvShows

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.django.R
import com.example.django.adapters.TvGridAdapter
import com.example.django.databinding.FragmentFavoritesTvshowsBinding
import com.example.django.ui.fragment.favorites.FavoritesFragmentDirections

class FavoritesTvShowsFragment : Fragment()  {

    private val viewModel: FavoritesTvShowsViewModel by lazy {
        ViewModelProviders.of(this).get(FavoritesTvShowsViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Favorites", "TvShow Favorites Fragment created")

        val binding: FragmentFavoritesTvshowsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorites_tvshows, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this


        // Giving the binding access to the ViewModel
        binding.viewModel = viewModel

        binding.favoriteTvshowsGrid.adapter = TvGridAdapter(TvGridAdapter.OnClickListener{
            viewModel.displayTvShowDetails(it)
        })

        viewModel.navigateToSelectedTvShow.observe(this, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(FavoritesFragmentDirections.showTvShowDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayTvShowDetailsComplete()
            }
        })


        return binding.root
    }
}