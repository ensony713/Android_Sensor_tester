package com.example.sensor_test.ui.sensor

import android.hardware.Sensor
import android.hardware.SensorManager
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
fun isGyroscopeSensorAvailable(): Boolean = isSensorAvailable(type = Sensor.TYPE_GYROSCOPE)

@Composable
@ReadOnlyComposable
fun getGyroscopeSensor(): Sensor = getSensor(type = Sensor.TYPE_GYROSCOPE)

@Composable
fun rememberGyroscopeSensorValueAsState(
    initialValue: SensorValue<Triple<Float, Float, Float>> = SensorValue.EMPTY_3D,
    samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_NORMAL
): State<SensorValue<Triple<Float, Float, Float>>> = rememberSensorValueAsState(
    type = Sensor.TYPE_GYROSCOPE,
    samplingPeriodUs = samplingPeriodUs,
    transformSensorEvent = { event -> event?.to3DSensorValue() ?: initialValue }
)

@Composable
fun GyroscopeDataView() {
    if (isGyroscopeSensorAvailable()) {
        val sensorValue by rememberGyroscopeSensorValueAsState()
        DataView(type = Type.GYROSCOPE, value = sensorValue.value)
    }
}
