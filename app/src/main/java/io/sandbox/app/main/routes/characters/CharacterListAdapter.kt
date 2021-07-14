package io.sandbox.app.main.routes.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.sandbox.R
import io.sandbox.app.extension.load
import io.sandbox.data.network.model.response.CharacterResponse
import io.sandbox.data.type.CharacterStatus
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_character.*

class CharacterListAdapter(
    private val onItemSelected: (CharacterResponse) -> Unit
) : PagingDataAdapter<CharacterResponse, CharacterListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false), onItemSelected
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(
        override val containerView: View,
        private val onItemSelected: (CharacterResponse) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: CharacterResponse?) {
            containerView.setOnClickListener { item?.let(onItemSelected) }
            image.load(item?.image)
            name.text = item?.name
            location.text = item?.location?.name
            species.text = item?.species
            status.setStatus(item?.status)
        }

        private fun TextView.setStatus(status: CharacterStatus?) {
            if (status == null) return
            val res = containerView.resources
            text = res.getString(R.string.home_list_item_status, res.getString(status.stringResId))
            setTextColor(ContextCompat.getColor(context, status.colorResId))
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<CharacterResponse>() {
        override fun areItemsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse) = oldItem == newItem
    }
}