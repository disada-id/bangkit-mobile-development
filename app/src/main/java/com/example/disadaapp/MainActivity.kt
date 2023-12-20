package com.example.disadaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.disadaapp.ui.theme.DisadaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(Manifest.permission.RECORD_AUDIO),
//            0
//        )
        setContent {
            DisadaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisadaApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisadaAppPreview() {
    DisadaAppTheme {
        DisadaApp()
    }
}