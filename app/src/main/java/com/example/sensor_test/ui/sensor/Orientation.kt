package com.example.sensor_test.ui.sensor

import android.hardware.SensorManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sensor_test.data.Type
import com.example.sensor_test.ui.DataView
import com.example.sensor_test.ui.Line
import com.example.sensor_test.ui.MatrixDataView

@Composable
@ReadOnlyComposable
fun isOrientationAvailable(): Boolean = isMagneticSensorAvailable() && isAccelerometerSensorAvailable()

@Composable
fun OrientationDataView() {
    if (isOrientationAvailable()) {
        val accelerometerState by rememberAccelerometerSensorAsState()
        val magneticFieldState by rememberMagneticFieldSensorValueAsState()

        val accelerometerReading = FloatArray(3)
        val magneticFieldReading = FloatArray(3)
        val rotationMatrix = FloatArray(9)
        val orientationAngles = FloatArray(3)

        val av = accelerometerState.value
        val mv = magneticFieldState.value

        accelerometerReading[0] = av.first
        accelerometerReading[1] = av.second
        accelerometerReading[2] = av.third

        magneticFieldReading[0] = mv.first
        magneticFieldReading[1] = mv.second
        magneticFieldReading[2] = mv.third

        SensorManager.getRotationMatrix(
            rotationMatrix,
            null,
            accelerometerReading,
            magneticFieldReading
        )

        SensorManager.getOrientation(rotationMatrix, orientationAngles)
        val (z, x, y) = orientationAngles

        Column {
            MatrixDataView(type = Type.ROTATION_MATRIX, value = rotationMatrix)
            Spacer(modifier = Modifier.height(10.dp))
            Line()
            DataView(type = Type.ORIENTATION, value = Triple(x, y, z))
        }
    }
    else {
        Text(text = "Orientation value is not Available")
    }
}
