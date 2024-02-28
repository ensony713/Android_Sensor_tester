package com.example.sensor_test

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.sensor_test.ui.SensorViewLayout
import com.example.sensor_test.ui.theme.Sensor_testTheme

private const val Tag = "MainActivity"

class MainActivity : ComponentActivity() {

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        super.onCreate(savedInstanceState)

        Log.d(Tag, "onCreate called")

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        
        setContent {
            Sensor_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //SensorDataView()
                    SensorViewLayout()
                }
            }
        }
    }
}
