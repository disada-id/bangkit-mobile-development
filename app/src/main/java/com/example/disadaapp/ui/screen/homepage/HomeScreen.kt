package com.example.disadaapp.ui.screen.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.disadaapp.Utils.AudioService
import com.example.disadaapp.Utils.AudioViewModel
import com.example.disadaapp.data.model.AudioData
import com.example.disadaapp.ui.Component.ButtonCustomRecord
import com.example.disadaapp.ui.Component.CardCustom
import com.example.disadaapp.ui.theme.DisadaAppTheme
import java.io.File

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: AudioViewModel = hiltViewModel()
) {

    var audioFile: File? = null
    val cacheDir = LocalContext.current.cacheDir
    var mediaRecorder by remember {
        mutableStateOf<AudioService?>(null)
    }
    var playerRecorder by remember {
        mutableStateOf<AudioService?>(null)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        Column(
            modifier = modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box {
                CardCustom(modifier = modifier)
            }
            Spacer(modifier = modifier.height(5.dp))
            ButtonCustomRecord(
                onClick = {
                    File("audio.wav").also {
                        mediaRecorder?.startRecorder(it)
                        audioFile = it
                    }
                    viewModel.predictAudio(audioData = AudioData(ByteArray(10)))
                })
            Row() {
                Button(onClick = {
                    playerRecorder?.play(audioFile ?: return@Button)
                }) {
                    Text(text = "Play")
                }
                Button(onClick = {
                    playerRecorder?.stop()
                }) {
                    Text(text = "Stop playing")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisadaAppPreview() {
    DisadaAppTheme {
        HomeScreen()
    }
}