package com.example.django.ui.fragment.search

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.example.django.adapters.SearchAdapter
import com.example.django.model.helpers.Searchable
import com.example.django.ui.fragment.discover.DiscoverFragmentDirections
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*

class SearchFragment : BaseSearchFragment(), SearchInterface {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    private var currentPaginationSubscription: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.navigateToSelectedMovie.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(DiscoverFragmentDirections.showMovieDetail(it))
                    viewModel.displayMovieDetailsComplete()
            }
        })

        viewModel.navigateToSelectedTvShow.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(DiscoverFragmentDirections.showTvShowDetail(it))
                viewModel.displayTvShowDetailsComplete()
            }
        })

        viewModel.navigateToSelectedPerson.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(DiscoverFragmentDirections.showPersonDetail(it))
                viewModel.displayPersonDetailsComplete()
            }
        })

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.query.onBackpressureDrop()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ query ->
                currentPaginationSubscription?.let {
                    disposables.remove(it)
                }

                setPageNumber(0)
                viewModel.list.clear()
                list.adapter = null
                loading.visibility = View.VISIBLE

                paginator.onBackpressureDrop()
                    .concatMap {
                        viewModel.getSearchResults(query, it).toFlowable(BackpressureStrategy.LATEST)
                            .subscribeOn(Schedulers.io())
                    }?.map {
                        it.results ?: listOf()
                    }
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                        { results ->
                            if (list?.adapter == null) {
                                if (viewModel.pageNumber == 1) {
                                    viewModel.list.clear()
                                }
                                viewModel.list.addAll(results)
                                list?.adapter = SearchAdapter(viewModel.list)
                            } else {
                                val newList = mutableListOf<Searchable>().apply {
                                    addAll((list.adapter as SearchAdapter).items)
                                    addAll(results)
                                }
                                val diffCallback = SearchDiffCallback(viewModel.list, newList)
                                viewModel.list.addAll(results)
                                DiffUtil.calculateDiff(diffCallback)
                                    .dispatchUpdatesTo(list.adapter as SearchAdapter)
                            }
                            finishLoading()
                        }, this::handleError
                    )?.let {
                        currentPaginationSubscription = it
                        disposables.add(it)
                    }
                nextPage()
            }, this::handleError)?.let {
                disposables.add(it)
            }
        Handler().post {
            arguments?.getString(SearchInterface.QUERY)?.let {
                search(it)
            }
        }
    }

    override fun setPageNumber(pageNumber: Int) {
        viewModel.pageNumber = pageNumber
    }

    override fun getPageNumber(): Int {
        return viewModel.pageNumber
    }

    override fun search(query: String) {
        viewModel.query.onNext(query)
    }
}

class SearchDiffCallback(private val mOldSearch: List<Searchable>, private val mNewSearch: List<Searchable>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldSearch[oldItemPosition]._id === mNewSearch[newItemPosition]._id
    }

    override fun getOldListSize(): Int {
        return mOldSearch.size
    }

    override fun getNewListSize(): Int {
        return mNewSearch.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldSearch[oldItemPosition]._id === mNewSearch[newItemPosition]._id
    }
}
