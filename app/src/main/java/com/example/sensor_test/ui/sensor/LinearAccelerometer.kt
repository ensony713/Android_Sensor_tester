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
fun isLinearAccelerationSensorAvailable(): Boolean = isSensorAvailable(type = Sensor.TYPE_LINEAR_ACCELERATION)

@Composable
fun getLinearAccelerationSensor(): Sensor = getSensor(type = Sensor.TYPE_LINEAR_ACCELERATION)

@Composable
fun rememberLinearAccelerationValueAsState(
    initialValue: SensorValue<Triple<Float, Float, Float>> = SensorValue.EMPTY_3D,
    samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_NORMAL
): State<SensorValue<Triple<Float, Float, Float>>> = rememberSensorValueAsState(
    type = Sensor.TYPE_LINEAR_ACCELERATION,
    samplingPeriodUs = samplingPeriodUs,
    transformSensorEvent = { event -> event?.to3DSensorValue() ?: initialValue }
)

@Composable
fun LinearAccelerationDataView() {
    if (isLinearAccelerationSensorAvailable()) {
        val sensorValue by rememberLinearAccelerationValueAsState()
        DataView(type = Type.LINEAR_ACCELEROMETER, value = sensorValue.value)
    } else {
        Text(text = "Linear Acceleration Sensor is not Available")
    }
}