package com.example.django.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.django.R
import com.example.django.ui.fragment.search.SearchInterface
import com.example.django.ui.fragment.search.SearchViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController : NavController
    val disposables = CompositeDisposable()


    private val searchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentID) as NavHostFragment
        navController = navHostFragment.navController

        bottom_nav_view.setupWithNavController((navHostFragment.navController))

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        // Set up the query listener that executes the search
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    subscriber.onNext(newText)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    subscriber.onNext(query)
                    return false
                }
            })
        }).map { text -> text.toLowerCase().trim() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter { text -> text.isNotBlank() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { text ->
                search(text)
            }.let{
                disposables.add(it)
            }

        return super.onCreateOptionsMenu(menu)
    }

    private fun search(text: String?) {
        Log.d("subscriber", text)

        if (navController.currentDestination?.id != R.id.searchFragment) {
            val args = Bundle().apply { putString(SearchInterface.QUERY, text) }
            navController.navigate(R.id.searchFragment, args)
        } else {
            searchViewModel.query.onNext(text)
        }
    }
}
