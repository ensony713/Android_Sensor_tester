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
fun isGravitySensorAvailable(): Boolean = isSensorAvailable(type = Sensor.TYPE_GRAVITY)

@Composable
@ReadOnlyComposable
fun getGravitySensor(): Sensor = getSensor(type = Sensor.TYPE_GRAVITY)

@Composable
fun rememberGravitySensorValueAsState(
    initialValue: SensorValue<Triple<Float, Float, Float>> = SensorValue.EMPTY_3D,
    samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_NORMAL
): State<SensorValue<Triple<Float, Float, Float>>> = rememberSensorValueAsState(
    type = Sensor.TYPE_GRAVITY,
    samplingPeriodUs = samplingPeriodUs,
    transformSensorEvent = { event -> event?.to3DSensorValue() ?: initialValue }
)

@Composable
fun GravityDataView() {
    if (isGravitySensorAvailable()) {
        val sensorValue by rememberGravitySensorValueAsState()
        DataView(type = Type.GRAVITY, value = sensorValue.value)
    }
    else {
        Text(text = "Gravity Sensor is not Available")
    }
}
