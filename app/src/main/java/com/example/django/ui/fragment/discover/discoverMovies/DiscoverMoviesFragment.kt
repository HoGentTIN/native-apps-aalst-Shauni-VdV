package com.example.django.ui.fragment.discover.discoverMovies

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.django.R
import com.example.django.adapters.MovieGridAdapter
import com.example.django.databinding.FragmentDiscoverMoviesBinding

class DiscoverMoviesFragment : Fragment() {

    private val viewModel: DiscoverMoviesViewModel by lazy {
        ViewModelProviders.of(this).get(DiscoverMoviesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i("Discover", "Movie Discover Fragment created")

        val binding : FragmentDiscoverMoviesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover_movies, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this


        // Giving the binding access to the DiscoverViewModel
        binding.viewModel = viewModel


        // Layout manager definitions
        var layoutManagerPopularMovies = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        var layoutManagerLatestMovies = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        var layoutManagerTopRatedMovies = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        val recyclerViewPopularMovies = binding.popularMoviesGrid as RecyclerView
        recyclerViewPopularMovies.setHasFixedSize(false)
        recyclerViewPopularMovies.setLayoutManager(layoutManagerPopularMovies)

        val recyclerViewLatestMovies = binding.latestMoviesGrid as RecyclerView
        recyclerViewLatestMovies.setHasFixedSize(false)
        recyclerViewLatestMovies.setLayoutManager(layoutManagerLatestMovies)

        val recyclerViewTopRatedMovies = binding.topRatedMoviesGrid as RecyclerView
        recyclerViewTopRatedMovies.setHasFixedSize(false)
        recyclerViewTopRatedMovies.setLayoutManager(layoutManagerTopRatedMovies)

        // Sets the adapter of the photosGrid RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
        binding.popularMoviesGrid.adapter = MovieGridAdapter(MovieGridAdapter.OnClickListener {
            viewModel.displayMovieDetails(it)
        })

        binding.latestMoviesGrid.adapter = MovieGridAdapter(MovieGridAdapter.OnClickListener {
            viewModel.displayMovieDetails(it)
        })

        binding.topRatedMoviesGrid.adapter = MovieGridAdapter(MovieGridAdapter.OnClickListener {
            viewModel.displayMovieDetails(it)
        })

        // Observe the navigateToSelectedMovieLiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedMovie.observe(this, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(DiscoverMoviesFragmentDirections.showMovieDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayMovieDetailsComplete()
            }
        })

        return binding.root
    }
}