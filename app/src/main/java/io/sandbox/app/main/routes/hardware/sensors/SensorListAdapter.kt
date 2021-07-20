package io.sandbox.app.main.routes.hardware.sensors

import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.sandbox.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_sensor.*

class SensorListAdapter(
    private val onSensorSelected: (Sensor) -> Unit
) : ListAdapter<Sensor, SensorListAdapter.ViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_sensor, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onSensorSelected)
    }

    class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(sensor: Sensor, onSensorSelected: (Sensor) -> Unit) {
            type.value.text = sensor.stringType
            name.value.text = sensor.name
            vendor.value.text = sensor.vendor
            version.value.text = sensor.version.toString()
            containerView.setOnClickListener { onSensorSelected(sensor) }
        }
    }

    private class ItemCallback : DiffUtil.ItemCallback<Sensor>() {
        override fun areItemsTheSame(oldItem: Sensor, newItem: Sensor) = oldItem.toString() == newItem.toString()
        override fun areContentsTheSame(oldItem: Sensor, newItem: Sensor) = oldItem.toString() == newItem.toString()
    }
}