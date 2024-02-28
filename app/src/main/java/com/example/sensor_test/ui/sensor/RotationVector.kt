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
import com.example.sensor_test.to4DSensorValue
import com.example.sensor_test.ui.DataView
import com.example.sensor_test.ui.Sensor4DDataView

@Composable
@ReadOnlyComposable
fun isRotationVectorSensorAvailable(): Boolean = isSensorAvailable(type = Sensor.TYPE_ROTATION_VECTOR)

@Composable
@ReadOnlyComposable
fun getRotationVectorSensor(): Sensor = getSensor(type = Sensor.TYPE_ROTATION_VECTOR)

@Composable
fun rememberRotationVectorSensorValueAsState(
    initialValue: SensorValue<List<Float>> = SensorValue.EMPTY_4D,
    samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_NORMAL
): State<SensorValue<List<Float>>> = rememberSensorValueAsState(
    type = Sensor.TYPE_ROTATION_VECTOR,
    samplingPeriodUs = samplingPeriodUs,
    transformSensorEvent = { event -> event?.to4DSensorValue() ?: SensorValue.EMPTY_4D }
)

@Composable
fun RotationVectorDataView() {
    if (isRotationVectorSensorAvailable()) {
        val sensorValue by rememberRotationVectorSensorValueAsState()
        Sensor4DDataView(type = Type.ROTATION_VECTOR, value = sensorValue.value)
    }
    else {
        Text(text = "Rotation Vector is not Available")
    }
}