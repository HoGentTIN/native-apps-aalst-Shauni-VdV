package com.example.django.ui.fragment.discover

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.django.R
import com.example.django.databinding.FragmentDiscoverBinding
import com.example.django.adapters.DiscoverPagerAdapter
import kotlinx.android.synthetic.main.fragment_discover.*


class DiscoverFragment : Fragment() {

    private val viewModel: DiscoverViewModel by lazy {
        ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewPager
        discover_viewpager.adapter = DiscoverPagerAdapter(childFragmentManager)
        discover_viewpager_tablayout.setupWithViewPager(discover_viewpager)

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

        return binding.root
    }

}

