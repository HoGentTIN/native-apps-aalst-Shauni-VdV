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
import com.example.django.adapters.bindRecyclerView
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.fragment_discover.view.*
import androidx.recyclerview.widget.GridLayoutManager



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


        var layoutManagerPopular = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        var layoutManagerLatest = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        var layoutManagerTopRated = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)


        val recyclerViewPopular = binding.popularMoviesGrid as RecyclerView
        recyclerViewPopular.setHasFixedSize(false)
        recyclerViewPopular.setLayoutManager(layoutManagerPopular)

        val recyclerViewLatest = binding.latestMoviesGrid as RecyclerView
        recyclerViewLatest.setHasFixedSize(false)
        recyclerViewLatest.setLayoutManager(layoutManagerLatest)

        val recyclerViewTopRated = binding.topRatedMoviesGrid as RecyclerView
        recyclerViewTopRated.setHasFixedSize(false)
        recyclerViewTopRated.setLayoutManager(layoutManagerTopRated)




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
                this.findNavController().navigate(DiscoverFragmentDirections.showDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayMovieDetailsComplete()
            }
        })
        return binding.root
    }

}

