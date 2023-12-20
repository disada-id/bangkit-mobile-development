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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.disadaapp.Utils.AudioService
import com.example.disadaapp.Utils.AudioService2
import com.example.disadaapp.Utils.AudioViewModel
import com.example.disadaapp.ui.Component.ButtonCustomRecord
import com.example.disadaapp.ui.Component.CardCustom
import java.io.File


@Composable
fun HomeScreen(
    recorder: AudioService2,
    modifier: Modifier = Modifier,
    viewModel: AudioViewModel = hiltViewModel()
) {
    var audioFile: File? = null
    val cacheDir = LocalContext.current.cacheDir
    val mediaRecorder by remember { mutableStateOf<AudioService?>(null) }
    val playerRecorder by remember { mutableStateOf<AudioService?>(null) }
    var isRecording by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box {
                CardCustom(modifier = modifier)
            }
            Spacer(modifier = modifier.height(5.dp))

            // Tampilan saat perekaman dimulai
            if (isRecording) {
                Text(
                    text = "Sedang merekam...",
                    color = Color.Red, // Atur warna teks sesuai kebutuhan
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            ButtonCustomRecord(
                onClick = {
                    if (isRecording) {
                        recorder.stopRecorder()
//                        mediaRecorder?.stopRecorder()
                        isRecording = false
                    } else {
                        // Memulai perekaman jika tidak sedang merekam
                        File(cacheDir, "audio.wav").also {
                            recorder.startRecorder(it)
//                            mediaRecorder?.startRecorder(it)
                            audioFile = it
                            isRecording = true
                            viewModel.predictAudio(audioFile!!)

                        }
                    }
                },
                modifier = Modifier.background(
                    if (isRecording) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                )
            )

            Row() {
                Button(onClick = {
                    recorder.play(audioFile ?: return@Button)
//                    playerRecorder?.play(audioFile ?: return@Button)
                }) {
                    Text(text = "Mainkan")
                }
                Button(onClick = {
                    recorder.stop()
//                    playerRecorder?.stop()
                }) {
                    Text(text = "Stop Pemutaran")
                }
            }
        }
    }
}
