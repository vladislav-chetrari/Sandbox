package io.sandbox.app.main.routes.hardware.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.sandbox.app.base.vm.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SensorListViewModel @Inject constructor(
    private val sensorManager: SensorManager
) : BaseViewModel() {

    private var activeSensorListener: SensorEventListener? = null

    //TODO add private liveData to manage sensor type
    val sensors = mutableLiveData(sensorManager.getSensorList(Sensor.TYPE_ALL).sortedBy { it.stringType })
    val activeSensorData = mutableLiveData<FloatArray?>()

    fun onSensorInfoDismiss() {
        activeSensorListener?.let(sensorManager::unregisterListener)
        activeSensorData.mutable.postValue(null)
    }

    fun onSensorSelected(sensor: Sensor) {
        onSensorInfoDismiss()
        val listener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
            override fun onSensorChanged(event: SensorEvent?) = activeSensorData.mutable.postValue(event?.values)
        }
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        activeSensorListener = listener
    }

    override fun onCleared() {
        onSensorInfoDismiss()
        super.onCleared()
    }
}