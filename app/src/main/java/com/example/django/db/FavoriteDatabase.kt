package com.example.django.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.django.model.Movie
import com.example.django.model.TvShow

@Database(entities = [Movie::class, TvShow::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract val favoriteDao : FavoriteDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE : FavoriteDatabase?= null

        fun getInstance(context: Context) : FavoriteDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "movie_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}