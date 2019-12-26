package com.example.django.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.django.databinding.TvGridItemBinding
import com.example.django.model.TvShow

class TvGridAdapter(val onClickListener: OnClickListener) :
    ListAdapter<TvShow, TvGridAdapter.TvViewHolder>(DiffCallback) {
    /**
     * The MovieViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [TvShow] information.
     */
    class TvViewHolder(private var binding: TvGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            binding.property = tvShow
            binding.tvPoster.minimumHeight = 200
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [TvShow]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvViewHolder {
        return TvViewHolder(TvGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(tvShow)
        }
        holder.bind(tvShow)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [TvSHow]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [TvShow]
     */
    class OnClickListener(val clickListener: (tvShow: TvShow) -> Unit) {
        fun onClick(tvShow: TvShow) = clickListener(tvShow)
    }
}
