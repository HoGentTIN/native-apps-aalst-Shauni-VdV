package com.example.django.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.django.ui.fragment.discover.discoverMovies.DiscoverMoviesFragment
import com.example.django.ui.fragment.discover.discoverTvShows.DiscoverTvShowsFragment

class DiscoverPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return DiscoverMoviesFragment()
        }
        return DiscoverTvShowsFragment()
    }

    override fun getPageTitle(position: Int): CharSequence {
        // Show the heading 'New' at index 0
        if (position == 0) return "Movies"
        // Show the heading 'Favorites' at index 1
        else return "TV Shows"
    }
}
