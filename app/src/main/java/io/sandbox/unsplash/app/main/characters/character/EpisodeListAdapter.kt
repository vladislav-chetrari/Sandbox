package io.sandbox.unsplash.app.main.characters.character

import android.text.util.Linkify.WEB_URLS
import android.text.util.Linkify.addLinks
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.sandbox.unsplash.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_episode.*

class EpisodeListAdapter : ListAdapter<String, EpisodeListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_episode, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: String) {
            episodeLink.text = item
            //TODO instead of linkify, open another fragment with episode
            addLinks(episodeLink, WEB_URLS)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }
}
