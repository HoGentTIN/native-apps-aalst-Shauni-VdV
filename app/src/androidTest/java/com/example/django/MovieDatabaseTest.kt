package com.example.django

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.django.db.MovieDatabase
import com.example.django.db.MovieDatabaseDao
import com.example.django.model.Movie
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieDatabaseTest {

    private lateinit var movieDao: MovieDatabaseDao
    private lateinit var testMovie: Movie
    private lateinit var testMovie2: Movie
    private lateinit var movieDatabase: MovieDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        movieDatabase = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        movieDao = movieDatabase.movieDao

        testMovie = Movie(
            id = "1",
            title = "First Movie",
            overview = "This is an overview for a test movie",
            isFavorite = false
        )

        testMovie2 = Movie(
            id = "2",
            title = "Second Movie",
            overview = "This is an overview for a test movie",
            isFavorite = true
        )
    }

    @After
    fun closeDb() {
        movieDatabase.close()
    }

    @Test
    fun insertMovie_GetMovie_ReturnsMovie() {
        runBlocking {
            movieDao.insert(testMovie)
            val movie = movieDao.getMovie(testMovie.id)
            assertEquals(movie?.title, testMovie.title)
        }
    }

    @Test
    fun insertMovie_CorrectAmountOfMoviesInDatabase() {
        runBlocking {
            movieDao.insert(testMovie)
            movieDao.insert(testMovie2)
            assertEquals(movieDao.getMovieList()?.count(), 2)
        }
    }

    @Test
    fun duplicateInsert_DoesNotAddDuplicate() {
        runBlocking {
            movieDao.insert(testMovie)
            movieDao.insert(testMovie)
            assertEquals(movieDao.getMovieList()?.count(), 1)
        }
    }

    @Test
    fun addToFavorites_ReturnFavoriteMovies() {
        runBlocking {
            movieDao.insert(testMovie2)
            movieDao.insert(testMovie)
            assertEquals(movieDao.getFavoriteMovies()?.count(), 1)
        }
    }
}
