package com.example.sensor_test.ui.sensor

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import com.example.sensor_test.SensorValue
import com.example.sensor_test.data.Type
import com.example.sensor_test.getSensor
import com.example.sensor_test.isSensorAvailable
import com.example.sensor_test.rememberSensorValueAsState
import com.example.sensor_test.to3DSensorValue
import com.example.sensor_test.ui.DataView

@Composable
@ReadOnlyComposable
fun isAccelerometerSensorAvailable(): Boolean = isSensorAvailable(type = Sensor.TYPE_ACCELEROMETER)

@Composable
@ReadOnlyComposable
fun getAccelerometerSenor(): Sensor = getSensor(type = Sensor.TYPE_ACCELEROMETER)

@Composable
fun rememberAccelerometerSensorAsState(
    initialValue: SensorValue<Triple<Float, Float, Float>> = SensorValue.EMPTY_3D,
    samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_NORMAL
): State<SensorValue<Triple<Float, Float, Float>>> = rememberSensorValueAsState(
    type = Sensor.TYPE_ACCELEROMETER,
    samplingPeriodUs = samplingPeriodUs,
    transformSensorEvent = { event -> event?.to3DSensorValue() ?: initialValue }
)

@Composable
fun AccelerometerDataView() {
    if (isAccelerometerSensorAvailable()) {
        val sensorValue by rememberAccelerometerSensorAsState()
        DataView(type = Type.ACCELEROMETER, value = sensorValue.value)
    }
    else {
        Text(text = "Accelerometer sensor is not available")
    }
}