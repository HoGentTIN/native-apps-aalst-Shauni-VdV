package com.example.django.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.django.model.helpers.Searchable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "person_table")
data class Person(

    val birthday: String,
    val known_for_department: String,
    val deathday: String? = null,

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val name: String,
    val also_known_as: List<String>,
    val gender: Int,
    val biography: String,
    val popularity: Number,
    val place_of_birth: String? = null,
    val profile_path: String? = null,
    val adult: Boolean? = false,
    val imdb_id: String,
    val homepage: String? = null

) : Parcelable, Searchable(PERSON) {

    fun getProfile(): String {
        return "https://image.tmdb.org/t/p/w500/$profile_path"
    }
}
