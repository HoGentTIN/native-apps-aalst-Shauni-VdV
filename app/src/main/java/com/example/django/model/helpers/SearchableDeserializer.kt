package com.example.django.model.helpers

import android.util.Log
import com.example.django.App
import com.example.django.model.Movie
import com.example.django.model.Person
import com.example.django.model.TvShow
import com.example.django.model.helpers.Searchable.Companion.MOVIE
import com.example.django.model.helpers.Searchable.Companion.PERSON
import com.example.django.model.helpers.Searchable.Companion.TV
import com.google.gson.*
import java.lang.reflect.Type

class SearchableDeserializer : JsonDeserializer<Searchable> {

    init {
        App.appComponent.inject(this)
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Searchable? {
        val gson = Gson()
        var item: Searchable? = null
        val rootObject = json.asJsonObject
        val type = rootObject.get("media_type").asString
        when (type) {
            MOVIE -> item = gson.fromJson<Movie>(json, Movie::class.java)
            TV -> item = gson.fromJson<TvShow>(json, TvShow::class.java)
            PERSON -> item = gson.fromJson<Person>(json, Person::class.java)
            else -> Log.d("TAG", "Invalid type: $type")
        }
        return item
    }
}
