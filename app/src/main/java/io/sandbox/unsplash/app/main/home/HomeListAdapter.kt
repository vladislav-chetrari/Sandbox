package io.sandbox.unsplash.app.main.home

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.sandbox.unsplash.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_home.*

class HomeListAdapter : ListAdapter<String, HomeListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(View.inflate(parent.context, R.layout.list_item_home, null))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: String) {
            text.text = item
            text.setOnClickListener {
                val direction = HomeFragmentDirections.actionMainNavHomeToNumberFragment(item)
                it.findNavController().navigate(direction)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem === newItem
        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }
}