package com.example.django.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.django.R
import com.example.django.databinding.FragmentTvshowDetailBinding
import com.example.django.db.TvShowDatabase

class DetailTvShowFragment : Fragment() {

    private val viewModel: DetailTvShowViewModel by lazy {
        ViewModelProviders.of(this).get(DetailTvShowViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application

        val tvShowDatabase = TvShowDatabase.getInstance(requireContext())
        val tvDao = tvShowDatabase.tvShowDao

        val binding = FragmentTvshowDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val tvShow = DetailTvShowFragmentArgs.fromBundle(arguments!!).selectedShow

        val viewModelFactory = DetailTvShowViewModelFactory(tvShow, application)

        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(DetailTvShowViewModel::class.java)

        binding.viewModel = viewModel

        if (viewModel.tvShow.isFavorite) {
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite_black_24dp)
        }

        binding.favoriteButton.setOnClickListener(View.OnClickListener {

            // Show is not favorite
            if (!tvShow.isFavorite) {
                Thread {
                    tvDao.addTvShowToFavorites(tvShow.id)
                }.start()
                tvShow.isFavorite = true
                binding.favoriteButton.setImageResource(R.drawable.ic_favorite_black_24dp)
                Toast.makeText(
                    it.context,
                    tvShow.name + " was added to favorites",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Thread {
                    tvDao.deleteTvShowFromFavorites(tvShow.id)
                }.start()
                tvShow.isFavorite = false

                binding.favoriteButton.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                Toast.makeText(
                    it.context,
                    tvShow.name + " was removed from favorites",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        return binding.root
    }
}
