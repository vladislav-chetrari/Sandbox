package io.sandbox.app.main.routes.hardware.sensors

import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.sandbox.databinding.ListItemSensorBinding

class SensorListAdapter(
    private val onSensorSelected: (Sensor) -> Unit
) : ListAdapter<Sensor, SensorListAdapter.ViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemSensorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onSensorSelected)
    }

    class ViewHolder(
        private val binding: ListItemSensorBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(sensor: Sensor, onSensorSelected: (Sensor) -> Unit) {
            binding.type.value.text = sensor.stringType
            binding.name.value.text = sensor.name
            binding.vendor.value.text = sensor.vendor
            binding.version.value.text = sensor.version.toString()
            itemView.setOnClickListener { onSensorSelected(sensor) }
        }
    }

    private class ItemCallback : DiffUtil.ItemCallback<Sensor>() {
        override fun areItemsTheSame(oldItem: Sensor, newItem: Sensor) = oldItem.toString() == newItem.toString()
        override fun areContentsTheSame(oldItem: Sensor, newItem: Sensor) = oldItem.toString() == newItem.toString()
    }
}