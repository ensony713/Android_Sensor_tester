package com.example.sensor_test.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sensor_test.R
import com.example.sensor_test.data.Type
import com.example.sensor_test.ui.sensor.AccelerometerDataView
import com.example.sensor_test.ui.sensor.GravityDataView
import com.example.sensor_test.ui.sensor.GyroscopeDataView
import com.example.sensor_test.ui.sensor.LinearAccelerationDataView
import com.example.sensor_test.ui.sensor.MagneticFieldDataView
import com.example.sensor_test.ui.sensor.MagneticFieldUncalibrationDataView
import com.example.sensor_test.ui.sensor.OrientationDataView
import com.example.sensor_test.ui.sensor.RotationVectorDataView

@Composable
fun Line(modifier: Modifier = Modifier) {
    Divider(
        color = colorResource(R.color.gray_200),
        modifier = modifier.padding(horizontal = 5.dp)
    )
}

@Composable
fun SensorViewLayout() {
    var selectOption by remember { mutableStateOf(Type.ACCELEROMETER)}

    Column {
        SensorDataView(
            type = selectOption,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.48f)
        )
        Line()
        SensorSelectButton(
            type = selectOption,
            onClick = { type -> selectOption = type },
            modifier = Modifier
                .fillMaxHeight()
        )
    }
}

@Composable
fun SensorSelectButton(
    type: Type,
    onClick: (Type) -> Unit,
    modifier: Modifier = Modifier
) {
    val basePadding = 16.dp
    val radioGroup = listOf(Type.ACCELEROMETER, Type.LINEAR_ACCELEROMETER, Type.GRAVITY,
        Type.MAGNETIC_FIELD, Type.MAGNETIC_FIELD_UNCALIBRATION, Type.ORIENTATION,
        Type.ROTATION_VECTOR, Type.GYROSCOPE)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        radioGroup.forEach{ selected ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = type == selected,
                    onClick = { onClick(selected) }
                )
                Icon(
                    painter = painterResource(R.drawable.bookmark),
                    contentDescription = stringResource(R.string.bookmark),
                    tint = if (selected.hw) Color.Red else Color.Black,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(16.dp)
                )
                Text(
                    text = stringResource(selected.title),
                    fontSize = optionFontSize
                )
            }
        }
    }
}

@Composable
fun SensorDataView(
    type: Type,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = Color.White)
            .padding(vertical = 10.dp)
    ) {
        when (type) {
            Type.ACCELEROMETER -> {
                AccelerometerDataView()
            }

            Type.GRAVITY -> {
                GravityDataView()
            }

            Type.GYROSCOPE -> {
                GyroscopeDataView()
            }

            Type.ROTATION_VECTOR -> {
                RotationVectorDataView()
            }

            Type.ORIENTATION -> {
                OrientationDataView()
            }

            Type.MAGNETIC_FIELD -> {
                MagneticFieldDataView()
            }

            Type.LINEAR_ACCELEROMETER -> {
                LinearAccelerationDataView()
            }

            Type.MAGNETIC_FIELD_UNCALIBRATION -> {
                MagneticFieldUncalibrationDataView()
            }

            else -> {
                Text(text = "Select the button")
            }
        }
    }
}

@Preview
@Composable
fun SensorViewLayoutPreview() {
    SensorSelectButton(type = Type.ACCELEROMETER, onClick = { })
}
