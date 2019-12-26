package com.example.django.injection.module

import android.content.Context
import com.example.django.model.repository.IMovieRepository
import com.example.django.model.repository.ITvShowRepository
import com.example.django.model.repository.MovieRepository
import com.example.django.model.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Context) {

    @Provides
    @Singleton
    internal fun provideMovieRepository(): IMovieRepository {
        return MovieRepository(application)
    }

    @Provides
    @Singleton
    internal fun provideTvShowRepository(): ITvShowRepository {
        return TvShowRepository(application)
    }
}
