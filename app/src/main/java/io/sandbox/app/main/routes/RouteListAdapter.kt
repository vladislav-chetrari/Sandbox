package io.sandbox.app.main.routes

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.sandbox.app.extension.color
import io.sandbox.databinding.ListItemRouteBinding

class RouteListAdapter(
    private val onRouteSelect: (Route) -> Unit
) : ListAdapter<Route, RouteListAdapter.ViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemRouteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onRouteSelect
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(
        private val binding: ListItemRouteBinding,
        private val onRouteSelect: (Route) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        val res: Resources
            get() = itemView.resources

        fun bind(route: Route) {
            binding.title.text = res.getString(route.titleResId)
            binding.description.text = res.getString(route.descriptionResId)
            itemView.setBackgroundColor(res.color(route.colorResId))
            itemView.setOnClickListener { onRouteSelect(route) }
        }
    }

    private class ItemCallback : DiffUtil.ItemCallback<Route>() {
        override fun areItemsTheSame(oldItem: Route, newItem: Route) = oldItem.ordinal == newItem.ordinal
        override fun areContentsTheSame(oldItem: Route, newItem: Route) = oldItem == newItem
    }
}