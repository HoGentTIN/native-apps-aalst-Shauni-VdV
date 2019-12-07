package com.example.django.injection.module

import android.content.Context
import android.os.Build
import com.example.django.BuildConfig
import com.example.django.model.Movie
import com.example.django.network.MovieService
import com.google.gson.GsonBuilder
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class NetworkModule(private val context: Context) {

    @Provides
    internal fun provideMovieService(retrofit: Retrofit) : MovieService{
        return retrofit.create(MovieService::class.java)
    }


    @Provides
    @Singleton
    internal fun provideRetrofitInterface(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(serviceInterceptor)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}



private val serviceInterceptor : Interceptor =
    Interceptor {
        chain ->
            val request = chain.request()
            val newUrl = request.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val newRequest = request.newBuilder()
                .url(newUrl)
                .method(request.method(), request.body())
                .build()
            chain.proceed(newRequest)
    }