package io.sandbox.unsplash.app.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.sandbox.unsplash.R
import io.sandbox.unsplash.app.extension.load
import io.sandbox.unsplash.data.model.Character
import io.sandbox.unsplash.data.model.CharacterStatus
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_character.*

class HomeListAdapter : PagedListAdapter<Character, HomeListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position) ?: Character())

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Character) {
            image.load(item.image)
            name.text = item.name
            location.text = item.location.name
            species.text = item.species
            status.setStatus(item.status)

            containerView.setOnClickListener {
                val direction = HomeFragmentDirections.actionMainNavHomeToCharacterFragment(item.id)
                it.findNavController().navigate(direction)
            }
        }

        private fun TextView.setStatus(status: CharacterStatus) {
            val res = containerView.resources
            text = res.getString(R.string.home_list_item_status, res.getString(status.stringResId))
            setTextColor(ContextCompat.getColor(context, status.colorResId))
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
    }
}