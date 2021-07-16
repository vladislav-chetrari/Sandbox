package io.sandbox.app.main.routes.hardware.sensors

import android.hardware.Sensor
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_INDEFINITE
import io.sandbox.R
import io.sandbox.app.base.view.BaseFragment
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.fragment_sensor_list.*

class SensorListFragment : BaseFragment(R.layout.fragment_sensor_list) {

    private val viewModel by viewModels<SensorListViewModel>()
    private val listAdapter = SensorListAdapter(::onSensorSelected)
    private val sensorDataSnackbar by lazy { Snackbar.make(coordinator, "", LENGTH_INDEFINITE) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
        list.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        list.adapter = listAdapter
        sensorDataSnackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines = 12
        sensorDataSnackbar.setAction(android.R.string.cancel) {
            viewModel.onSensorInfoDismiss()
            sensorDataSnackbar.dismiss()
        }
    }

    override fun onDestroyView() {
        list.adapter = null
        super.onDestroyView()
    }

    override fun observeLiveData() = viewModel.run {
        sensors.observe(listAdapter::submitList)
        activeSensorData.observe { data ->
            sensorDataSnackbar.run {
                if (data == null) dismiss()
                else {
                    setText(data.joinToString(separator = "\n"))
                    show()
                }
            }
        }
    }

    private fun onSensorSelected(sensor: Sensor) = viewModel.onSensorSelected(sensor)
}