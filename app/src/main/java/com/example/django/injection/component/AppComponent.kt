package com.example.django.injection.component

import com.example.django.App
import com.example.django.adapters.DiscoverPagerAdapter
import com.example.django.adapters.FavoritesPagerAdapter
import com.example.django.injection.module.DatabaseModule
import com.example.django.injection.module.NetworkModule
import com.example.django.model.helpers.SearchableDeserializer
import com.example.django.model.repository.MovieRepository
import com.example.django.model.repository.TvShowRepository
import com.example.django.ui.fragment.detail.DetailMovieViewModel
import com.example.django.ui.fragment.detail.DetailTvShowViewModel
import com.example.django.ui.fragment.discover.discoverMovies.DiscoverMoviesViewModel
import com.example.django.ui.fragment.discover.discoverTvShows.DiscoverTvShowsViewModel
import com.example.django.ui.fragment.discover.DiscoverViewModel
import com.example.django.ui.fragment.favorites.FavoritesViewModel
import com.example.django.ui.fragment.favorites.favoriteMovies.FavoritesMoviesViewModel
import com.example.django.ui.fragment.favorites.favoriteTvShows.FavoritesTvShowsViewModel
import com.example.django.ui.fragment.search.SearchViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules= [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(app: App)

    /* ViewModels */
    // Discover ----
    fun inject(discoverViewModel : DiscoverViewModel)
    fun inject(discoverMoviesViewModel: DiscoverMoviesViewModel)
    fun inject(discoverTvShowsViewModel: DiscoverTvShowsViewModel)

    // Favorites ----
    fun inject(favoritesViewModel: FavoritesViewModel)
    fun inject(favoritesMoviesViewModel: FavoritesMoviesViewModel)
    fun inject(favoritesTvShowsViewModel: FavoritesTvShowsViewModel)

    // Detail Pages ----
    fun inject(detailMovieViewModel: DetailMovieViewModel)
    fun inject(detailTvShowViewModel: DetailTvShowViewModel)

    // Search ----
    fun inject(searchViewModel: SearchViewModel)

    //PagerAdapter
    fun inject(discoverPagerAdapter: DiscoverPagerAdapter)
    fun inject(favoritesPagerAdapter: FavoritesPagerAdapter)

    //Deserializer
    fun inject(searchableDeserializer: SearchableDeserializer)

    // Repositories
    fun inject(movieRepository: MovieRepository)
    fun inject(tvShowRepository: TvShowRepository)


    @Component.Builder
    interface  Builder{
        fun build(): AppComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun databaseModule(databaseModule: DatabaseModule) : Builder
    }
}