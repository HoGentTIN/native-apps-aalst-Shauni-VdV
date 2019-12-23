package com.example.django.ui.fragment.favorites.favoriteMovies

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
import com.example.django.adapters.MovieGridAdapter
import com.example.django.databinding.FragmentFavoritesMoviesBinding
import com.example.django.ui.fragment.favorites.FavoritesFragmentDirections

class FavoritesMoviesFragment : Fragment() {
    private val viewModel: FavoritesMoviesViewModel by lazy {
        ViewModelProviders.of(this).get(FavoritesMoviesViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Favorites", "Movie Favorites Fragment created")

        val binding: FragmentFavoritesMoviesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorites_movies, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this


        // Giving the binding access to the ViewModel
        binding.viewModel = viewModel

        binding.favoriteMoviesGrid.adapter = MovieGridAdapter(MovieGridAdapter.OnClickListener{
            viewModel.displayMovieDetails(it)
        })

        viewModel.navigateToSelectedMovie.observe(this, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(FavoritesFragmentDirections.showMovieDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayMovieDetailsComplete()
            }
        })


        return binding.root
    }
}