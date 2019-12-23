package com.example.django.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tv")
data class TvShow(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val original_name: String? = null,
    val name: String? = null,
    val popularity: Double? = null,
    val vote_count: Int? = null,
    val first_air_date: String? = null,
    val backdrop_path: String? = null,
    val original_language: String? = null,
    val vote_average: Double? = null,
    val overview: String? = null,
    val poster_path: String? = null
) : Parcelable {
    fun getImg(): String {
        return "https://image.tmdb.org/t/p/w500/" + poster_path
    }
    fun getBackdrop() : String {
        return "https://image.tmdb.org/t/p/w500/" + backdrop_path
    }
}