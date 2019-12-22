package com.example.django.ui.fragment.discover.discoverTvShows

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
import com.example.django.adapters.TvGridAdapter
import com.example.django.databinding.FragmentDiscoverTvshowsBinding


class DiscoverTvShowsFragment : Fragment() {

    private val viewModel: DiscoverTvShowsViewModel by lazy {
        ViewModelProviders.of(this).get(DiscoverTvShowsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Discover", "Fragment created")

        val binding: FragmentDiscoverTvshowsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_discover_tvshows, container, false)
        //val binding = FragmentDiscoverBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this


        // Giving the binding access to the DiscoverViewModel
        binding.viewModel = viewModel


        var layoutManagerPopularTvShows =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)


        val recyclerViewPopularTvShows = binding.popularShowsGrid as RecyclerView
        recyclerViewPopularTvShows.setHasFixedSize(false)
        recyclerViewPopularTvShows.setLayoutManager(layoutManagerPopularTvShows)


        // Sets the adapter of the photosGrid RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked

        binding.popularShowsGrid.adapter = TvGridAdapter(TvGridAdapter.OnClickListener {
            viewModel.displayTvShowDetails(it)
        })


        viewModel.navigateToSelectedTvShow.observe(this, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(DiscoverTvShowsFragmentDirections.showTvShowDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayTvShowDetailsComplete()
            }
        })
        return binding.root

    }
}