package io.sandbox.app.main.routes.characters

import android.view.LayoutInflater
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
import io.sandbox.databinding.ListItemCharacterBinding

class CharacterListAdapter(
    private val onItemSelected: (CharacterResponse) -> Unit
) : PagingDataAdapter<CharacterResponse, CharacterListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemSelected
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(
        private val binding: ListItemCharacterBinding,
        private val onItemSelected: (CharacterResponse) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterResponse?) {
            itemView.setOnClickListener { item?.let(onItemSelected) }
            binding.image.load(item?.image)
            binding.name.text = item?.name
            binding.location.text = item?.location?.name
            binding.species.text = item?.species
            binding.status.setStatus(item?.status)
        }

        private fun TextView.setStatus(status: CharacterStatus?) {
            if (status == null) return
            val res = itemView.resources
            text = res.getString(R.string.home_list_item_status, res.getString(status.stringResId))
            setTextColor(ContextCompat.getColor(context, status.colorResId))
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<CharacterResponse>() {
        override fun areItemsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse) = oldItem == newItem
    }
}