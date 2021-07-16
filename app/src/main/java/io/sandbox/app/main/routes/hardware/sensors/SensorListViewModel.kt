package io.sandbox.app.main.routes.hardware.sensors

import android.hardware.Sensor
import android.hardware.SensorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.sandbox.app.base.vm.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SensorListViewModel @Inject constructor(
    private val sensorManager: SensorManager
) : BaseViewModel() {

    val sensors = mutableLiveData(sensorManager.getSensorList(Sensor.TYPE_ALL))
}