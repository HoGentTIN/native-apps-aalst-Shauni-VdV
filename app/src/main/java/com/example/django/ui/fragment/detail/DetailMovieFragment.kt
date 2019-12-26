package com.example.django.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.django.R
import com.example.django.databinding.FragmentMovieDetailBinding
import com.example.django.db.MovieDatabase

class DetailMovieFragment : Fragment() {

    private val viewModel: DetailMovieViewModel by lazy {
        ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application

        val movieDatabase = MovieDatabase.getInstance(requireContext())
        val movieDao = movieDatabase.movieDao

        val binding = FragmentMovieDetailBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        val movie = DetailMovieFragmentArgs.fromBundle(arguments!!).selectedMovie
        val viewModelFactory = DetailMovieViewModelFactory(movie, application)

        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(DetailMovieViewModel::class.java)

        binding.viewModel = viewModel

        // Set image if movie is favorited
        if (viewModel.movie.isFavorite) {
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite_black_24dp)
        }

        binding.favoriteButton.setOnClickListener(View.OnClickListener {

            // Movie is not favorite
            if (!movie.isFavorite) {
                Thread {
                    movieDao.addMovieToFavorites(movie.id)
                }.start()
                movie.isFavorite = true
                binding.favoriteButton.setImageResource(R.drawable.ic_favorite_black_24dp)
                Toast.makeText(
                    it.context,
                    movie.title + " was added to favorites",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Thread {
                    movieDao.deleteMovieFromFavorites(movie.id)
                }.start()
                movie.isFavorite = false

                binding.favoriteButton.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                Toast.makeText(
                    it.context,
                    movie.title + " was removed from favorites",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        binding.movieVoteText.text = getString(R.string.vote_count, movie.vote_average.toString())

        return binding.root
    }
}
