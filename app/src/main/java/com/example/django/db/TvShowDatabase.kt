package com.example.django.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.django.model.TvShow

@Database(entities = [TvShow::class], version = 1, exportSchema = false)
abstract class TvShowDatabase : RoomDatabase() {

    abstract val tvShowDao : TvShowDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE : TvShowDatabase?= null

        fun getInstance(context: Context) : TvShowDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TvShowDatabase::class.java,
                        "tvshow_database"
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