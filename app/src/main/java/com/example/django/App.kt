package com.example.django

import android.app.Application
import com.example.django.injection.component.AppComponent
import com.example.django.injection.component.DaggerAppComponent
import com.example.django.injection.module.DatabaseModule
import com.example.django.injection.module.NetworkModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .databaseModule(DatabaseModule(this))
            .networkModule(NetworkModule(this))
            .build()
    }
}
