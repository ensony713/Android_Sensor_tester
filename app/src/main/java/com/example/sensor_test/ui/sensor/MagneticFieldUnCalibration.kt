package com.example.sensor_test.ui.sensor

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sensor_test.SensorValue
import com.example.sensor_test.data.Type
import com.example.sensor_test.getSensor
import com.example.sensor_test.isSensorAvailable
import com.example.sensor_test.rememberSensorValueAsState
import com.example.sensor_test.to2TypeValue
import com.example.sensor_test.ui.DataView
import com.example.sensor_test.ui.Line

@Composable
@ReadOnlyComposable
fun isMagneticUncalibrationAvailable(): Boolean = isSensorAvailable(type = Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED)

@Composable
@ReadOnlyComposable
fun getMagneticUncalibrationSensor(): Sensor = getSensor(type = Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED)

@Composable
fun rememberMagneticUncalibrationValueAsState(
    initialValue: SensorValue<Pair<Triple<Float, Float, Float,>, Triple<Float, Float, Float>>>
        = SensorValue.EMPTY_2TYPE,
    samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_NORMAL
): State<SensorValue<Pair<Triple<Float, Float, Float,>, Triple<Float, Float, Float>>>>
= rememberSensorValueAsState(
    type = Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED,
    samplingPeriodUs = samplingPeriodUs,
    transformSensorEvent = { event -> event?.to2TypeValue() ?: initialValue }
)

@Composable
fun MagneticFieldUncalibrationDataView() {
    if (isMagneticUncalibrationAvailable()) {
        val sensorValue by rememberMagneticUncalibrationValueAsState()
        Column {
            DataView(type = Type.MAGNETIC_FIELD_UNCALIBRATION, value = sensorValue.value.first)
            Line()
            Text(text = "Hard Iron value", modifier = Modifier.padding(start = 10.dp))
            DataView(
                type = Type.MAGNETIC_FIELD_UNCALIBRATION,
                value = sensorValue.value.second,
                title = false
            )
        }
    } 
    else {
        Text(text = "Magnetic Field UnCalibration Sensor is not Available")
    }
}
