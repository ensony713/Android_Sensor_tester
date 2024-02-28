package com.example.sensor_test.data

import androidx.annotation.StringRes
import com.example.sensor_test.R

enum class Type(
    @StringRes val title: Int,
    val hw: Boolean
) {
    ACCELEROMETER(R.string.accelerometer, true),
    GRAVITY(R.string.gravity, true),
    GYROSCOPE(R.string.gyroscope, false),
    LINEAR_ACCELEROMETER(R.string.linear_accelerometer, true),
    MAGNETIC_FIELD(R.string.magnetic_field, true),
    ORIENTATION(R.string.orientation, false),
    ROTATION_VECTOR(R.string.rotation_vector, false),
    ROTATION_MATRIX(R.string.rotation_matrix, false),
    MAGNETIC_FIELD_UNCALIBRATION(R.string.magnetic_field_uncalibration, true)
}