package com.example.django

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.django.db.TvShowDatabase
import com.example.django.db.TvShowDatabaseDao
import com.example.django.model.TvShow
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class TvShowDatabaseTest {

    private lateinit var tvDao: TvShowDatabaseDao
    private lateinit var testShow: TvShow
    private lateinit var testShow2: TvShow
    private lateinit var tvDatabase: TvShowDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        tvDatabase = Room.inMemoryDatabaseBuilder(context, TvShowDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        tvDao = tvDatabase.tvShowDao

        testShow = TvShow(
            id = "1",
            name = "First Show",
            overview = "This is an overview for a test show",
            isFavorite = false
        )

        testShow2 = TvShow(
            id = "2",
            name = "Second Show",
            overview = "This is an overview for a test show",
            isFavorite = true
        )
    }

    @After
    fun closeDb() {
        tvDatabase.close()
    }

    @Test
    fun insertShow_GetShow_ReturnsCorrectShow() {
        runBlocking {
            tvDao.insert(testShow)
            val show = tvDao.getTvShow(testShow.id)
            assertEquals(show?.name, testShow.name)
        }
    }

    @Test
    fun insertShow_CorrectAmountOfShowsInDatabase() {
        runBlocking {
            tvDao.insert(testShow)
            tvDao.insert(testShow2)
            assertEquals(tvDao.getTvShowList()?.count(), 2)
        }
    }

    @Test
    fun duplicateInsert_DoesNotAddDuplicate() {
        runBlocking {
            tvDao.insert(testShow)
            tvDao.insert(testShow)
            assertEquals(tvDao.getTvShowList()?.count(), 1)
        }
    }

    @Test
    fun addToFavorites_ReturnFavoriteShows() {
        runBlocking {
            tvDao.insert(testShow2)
            tvDao.insert(testShow)
            assertEquals(tvDao.getFavoriteTvShows()?.count(), 1)
        }
    }
}
