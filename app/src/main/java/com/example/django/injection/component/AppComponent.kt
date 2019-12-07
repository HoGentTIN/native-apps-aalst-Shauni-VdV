package com.example.django.injection.component

import com.example.django.App
import com.example.django.injection.module.DatabaseModule
import com.example.django.injection.module.NetworkModule
import com.example.django.ui.fragment.Discover.DiscoverViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules= [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(discoverViewModel : DiscoverViewModel)
    fun inject(movieRepository: MovieRepository)


    @Component.Builder
    interface  Builder{
        fun build(): AppComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun databaseModule(databaseModule: DatabaseModule) : Builder
    }
}