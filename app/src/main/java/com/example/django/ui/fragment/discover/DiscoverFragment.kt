package com.example.django.ui.fragment.discover

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.django.R
import com.example.django.adapters.MovieGridAdapter
import com.example.django.databinding.FragmentDiscoverBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.django.adapters.TvGridAdapter


class DiscoverFragment : Fragment() {

    private val viewModel: DiscoverViewModel by lazy {
        ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i("Discover", "Fragment created")

        val binding : FragmentDiscoverBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false)
        //val binding = FragmentDiscoverBinding.inflate(inflater)

        activity?.setTitle("Discover")

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this


        // Giving the binding access to the DiscoverViewModel
        binding.viewModel = viewModel


        var layoutManagerPopularMovies = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        var layoutManagerLatestMovies = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        var layoutManagerTopRatedMovies = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)


        var layoutManagerPopularTvShows = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)




        val recyclerViewPopularMovies = binding.popularMoviesGrid as RecyclerView
        recyclerViewPopularMovies.setHasFixedSize(false)
        recyclerViewPopularMovies.setLayoutManager(layoutManagerPopularMovies)

        val recyclerViewLatestMovies = binding.latestMoviesGrid as RecyclerView
        recyclerViewLatestMovies.setHasFixedSize(false)
        recyclerViewLatestMovies.setLayoutManager(layoutManagerLatestMovies)

        val recyclerViewTopRatedMovies = binding.topRatedMoviesGrid as RecyclerView
        recyclerViewTopRatedMovies.setHasFixedSize(false)
        recyclerViewTopRatedMovies.setLayoutManager(layoutManagerTopRatedMovies)

        val recyclerViewPopularTvShows = binding.popularShowsGrid as RecyclerView
        recyclerViewPopularTvShows.setHasFixedSize(false)
        recyclerViewPopularTvShows.setLayoutManager(layoutManagerPopularTvShows)





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

        binding.popularShowsGrid.adapter = TvGridAdapter(TvGridAdapter.OnClickListener {
            viewModel.displayTvShowDetails(it)
        })

        // Observe the navigateToSelectedMovieLiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedMovie.observe(this, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(DiscoverFragmentDirections.showMovieDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayMovieDetailsComplete()
            }
        })

        viewModel.navigateToSelectedTvShow.observe(this, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(DiscoverFragmentDirections.showTvShowDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayTvShowDetailsComplete()
            }
        })
        return binding.root
    }

}

