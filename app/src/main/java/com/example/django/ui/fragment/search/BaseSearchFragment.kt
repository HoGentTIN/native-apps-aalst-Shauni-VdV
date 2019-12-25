package com.example.django.ui.fragment.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.django.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import kotlinx.android.synthetic.main.fragment_list.*

abstract class BaseSearchFragment : Fragment(){

    companion object{
        const val VISIBLE_TRESHOLD = 6
        const val TYPE_KEY: String = "type"
    }

    protected var disposables = CompositeDisposable()
    protected lateinit var paginator: PublishProcessor<Int>
    protected var isLoading = false

    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0

    protected abstract fun setPageNumber(pageNumber: Int)
    protected abstract fun getPageNumber(): Int

    private var onScrollChangeListener : RecyclerView.OnScrollListener?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var grid = GridLayoutManager(context, 2)
        list.layoutManager = grid
        grid.spanCount = 2


        onScrollChangeListener?.let { list.addOnScrollListener(it) }
        list.adapter?.let {
            if(it.itemCount == 0){
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE
            }
        } ?: run {
            loading.visibility = View.VISIBLE
        }

        list.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                lastVisibleItem = (list.layoutManager as? GridLayoutManager)?.findLastVisibleItemPosition() ?: 0
                if(!isLoading && totalItemCount <= lastVisibleItem + VISIBLE_TRESHOLD){
                    nextPage()
                    isLoading = true
                }
            }
        })

        paginator = PublishProcessor.create<Int>()

        swipe_refresh.setOnRefreshListener {
            list.adapter = null
            setPageNumber(0)
            totalItemCount = 0
            nextPage()
        }
    }

    override fun onResume() {
        super.onResume()
        loading?.visibility = View.GONE
    }

    protected fun nextPage(){
        isLoading = true
        setPageNumber(getPageNumber()+1)
        paginator.onNext(getPageNumber())
    }

    protected fun handleError(t: Throwable){
        Log.d("Error in list: ", t.message + Log.getStackTraceString(t))
        finishLoading()
        isLoading = false
        setPageNumber(getPageNumber()-1)
    }

    protected  fun finishLoading(){
        swipe_refresh?.isRefreshing = false
        totalItemCount = list?.adapter?.itemCount ?: 0
        isLoading = false
        loading?.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

}