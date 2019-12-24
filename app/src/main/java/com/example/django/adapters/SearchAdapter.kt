package com.example.django.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.django.R
import com.example.django.databinding.MovieGridItemBinding
import com.example.django.databinding.PersonGridItemBinding
import com.example.django.databinding.TvGridItemBinding
import com.example.django.model.Movie
import com.example.django.model.Person
import com.example.django.model.TvShow
import com.example.django.model.helpers.Searchable
import com.example.django.model.helpers.Searchable.Companion.MOVIE
import com.example.django.model.helpers.Searchable.Companion.PERSON
import com.example.django.model.helpers.Searchable.Companion.TV

class SearchAdapter(var items: List<Searchable>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val MOVIE_TYPE = 0
        const val TV_TYPE = 1
        const val PERSON_TYPE = 2
    }

    fun ViewGroup.inflate(adapter_layout: Int, attachToRoot: Boolean = false): View?{
        return LayoutInflater.from(context).inflate(adapter_layout,this,attachToRoot)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(items[position].mediaType){
        MOVIE -> (holder as? MovieGridAdapter.MovieViewHolder)?.bind(items[position] as Movie)
        TV -> (holder as? TvGridAdapter.TvViewHolder)?.bind(items[position] as TvShow)
        PERSON -> (holder as? PersonGridAdapter.PersonViewHolder)?.bind(items[position] as Person)
        else -> null
    }!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType){
        MOVIE_TYPE -> MovieGridAdapter.MovieViewHolder(MovieGridItemBinding())
        TV_TYPE -> TvGridAdapter.TvViewHolder(TvGridItemBinding())
        PERSON_TYPE -> PersonGridAdapter.PersonViewHolder(PersonGridItemBinding())
        else -> null
    }!!

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position].mediaType){
            MOVIE -> MOVIE_TYPE
            TV -> TV_TYPE
            PERSON -> PERSON_TYPE
            else -> -1
        }
    }


}