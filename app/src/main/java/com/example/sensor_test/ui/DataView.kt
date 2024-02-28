package com.example.sensor_test.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sensor_test.data.Type

val slicePadding = 6.dp

@Composable
fun Sensor4DDataView(
    type: Type,
    value: List<Float>
) {
    val index = listOf("X", "Y", "Z", "scalar")

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .background(color = Color.White)
    ) {
        Text(
            text = stringResource(id = type.title),
            fontSize = headerFontSize,
            style = TextStyle(lineHeight = headerFontSize),
            modifier = Modifier
                .fillMaxWidth()
        )
        for (i in 0..3) {
            Row {
                Text(
                    text = "${index[i]}: ",
                    fontSize = dataFontSize,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(0.25f)
                )
                Text(
                    text = floatToString(value[i]),
                    fontSize = dataFontSize,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 30.dp)
                )
            }
        }
        Text(text = "accuracy: ${value[4]}", fontSize = dataFontSize)
    }
}

@Composable
fun DataView(
    type: Type,
    value: Triple<Float, Float, Float>,
    index: List<String> = listOf("X", "Y", "Z"),
    title: Boolean = true
) {
    val data = value.toList()

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .background(color = Color.White)
    ) {
        if (title) {
            Text(
                text = stringResource(id = type.title),
                fontSize = headerFontSize,
                style = TextStyle(lineHeight = headerFontSize),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        for (i in 0..2) {
            Row {
                Text(
                    text = "${index[i]}: ",
                    fontSize = dataFontSize,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(0.2f)
                )
                Text(
                    text = floatToString(data[i]),
                    fontSize = dataFontSize,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 30.dp)
                )
            }
        }
    }
}

@Composable
fun MatrixDataView(
    type: Type,
    value: FloatArray
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 16.dp, vertical = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = type.title),
                fontSize = headerFontSize,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.width(slicePadding))
            Column {
                for (i in 0..2) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            floatToString(value[i + 0]),
                            fontSize = dataSmallerFontSize,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth(0.33f)
                        )
                        Text(
                            floatToString(value[i + 1]),
                            fontSize = dataSmallerFontSize,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                        Text(
                            floatToString(value[i + 2]),
                            fontSize = dataSmallerFontSize,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth(1f)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DataViewPreview() {
    //DataView(type = Type.LINEAR_ACCELEROMETER, value = Triple(0.123123123132f, 0.232333f, 0.21342843921892839128f))
    MatrixDataView(type = Type.ORIENTATION, value = FloatArray(9) {
        it + -0.10348194f
    })
}