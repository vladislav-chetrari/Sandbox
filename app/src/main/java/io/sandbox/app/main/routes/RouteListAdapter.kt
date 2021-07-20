package io.sandbox.app.main.routes

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.sandbox.R
import io.sandbox.app.extension.color
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_route.*

class RouteListAdapter(
    private val onRouteSelect: (Route) -> Unit
) : ListAdapter<Route, RouteListAdapter.ViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_route, parent, false),
        onRouteSelect
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(
        override val containerView: View,
        private val onRouteSelect: (Route) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        val res: Resources
            get() = containerView.resources

        fun bind(route: Route) {
            title.text = res.getString(route.titleResId)
            description.text = res.getString(route.descriptionResId)
            containerView.setBackgroundColor(res.color(route.colorResId))
            containerView.setOnClickListener { onRouteSelect(route) }
        }
    }

    private class ItemCallback : DiffUtil.ItemCallback<Route>() {
        override fun areItemsTheSame(oldItem: Route, newItem: Route) = oldItem.ordinal == newItem.ordinal
        override fun areContentsTheSame(oldItem: Route, newItem: Route) = oldItem == newItem
    }
}