package com.example.django.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.django.databinding.PersonGridItemBinding
import com.example.django.model.Person

class PersonGridAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Person, PersonGridAdapter.PersonViewHolder>(DiffCallback) {
    /**
     * The PersonViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [Person] information.
     */
    class PersonViewHolder(private var binding: PersonGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.property = person
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Person]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {
        return PersonViewHolder(PersonGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(person)
        }
        holder.bind(person)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Person]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Person]
     */
    class OnClickListener(val clickListener: (person: Person) -> Unit) {
        fun onClick(person: Person) = clickListener(person)
    }
}
