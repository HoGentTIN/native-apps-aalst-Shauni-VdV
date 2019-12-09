package com.example.django.ui.fragment.discover

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.django.adapters.MovieGridAdapter
import com.example.django.databinding.FragmentDiscoverBinding


class DiscoverFragment : Fragment() {

    private val viewModel: DiscoverViewModel by lazy {
        ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentDiscoverBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the DiscoverViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
        binding.moviesGrid.adapter = MovieGridAdapter(MovieGridAdapter.OnClickListener {
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

