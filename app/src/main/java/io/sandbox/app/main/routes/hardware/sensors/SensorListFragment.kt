package io.sandbox.app.main.routes.hardware.sensors

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import io.sandbox.app.base.view.BaseFragment
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.fragment_sensor_list.*

class SensorListFragment : BaseFragment(R.layout.fragment_sensor_list) {

    private val viewModel by viewModels<SensorListViewModel>()
    private val listAdapter = SensorListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
        list.adapter = listAdapter
    }

    override fun onDestroyView() {
        list.adapter = null
        super.onDestroyView()
    }

    override fun observeLiveData() = viewModel.sensors.observe(listAdapter::submitList)
}