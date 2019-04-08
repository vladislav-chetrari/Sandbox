package io.sandbox.unsplash.app.main.home

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayoutManager
import io.sandbox.unsplash.R
import io.sandbox.unsplash.data.model.Cat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_home.*

class HomeListAdapter : ListAdapter<Cat, HomeListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(View.inflate(parent.context, R.layout.list_item_home, null))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Cat) {
            image.layoutParams.let { if (it is FlexboxLayoutManager.LayoutParams) it.flexGrow = 1f }
            Glide.with(containerView)
                .load(item.imageUrl)
                .override(item.width, item.height)
                .into(image)

            containerView.setOnClickListener {
                val direction = HomeFragmentDirections.actionMainNavHomeToNumberFragment(item.id)
                it.findNavController().navigate(direction)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Cat, newItem: Cat) = oldItem == newItem
    }
}