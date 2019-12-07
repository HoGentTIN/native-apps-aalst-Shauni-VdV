package com.example.django.ui.fragment.Discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.django.R
import com.example.django.databinding.FragmentDiscoverBinding
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class DiscoverFragment : Fragment() {


    private lateinit var binding : FragmentDiscoverBinding
    private val viewModel: DiscoverViewModel by lazy {
        ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
    }

    companion object {
        fun newInstance() = DiscoverFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTitle("Discover")
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_discover,
            container,
            false
        )
        binding.setLifecycleOwner(this)

        initializeSwipeRefreshLayout()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun initializeSwipeRefreshLayout(){
        var mSwipeRefreshLayout = binding.swipeContainer
        mSwipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener{
            // Update
            viewModel.getMovies()
            mSwipeRefreshLayout.isRefreshing = false
        })
    }

}

