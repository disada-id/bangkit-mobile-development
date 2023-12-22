package com.example.disadaapp.ui.screen.homepage

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
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
import com.example.disadaapp.UiState
import com.example.disadaapp.Utils.AudioService
import com.example.disadaapp.Utils.AudioService2
import com.example.disadaapp.Utils.AudioViewModel
import com.example.disadaapp.ui.Component.ButtonCustomRecord
import com.example.disadaapp.ui.Component.CardCustom
import com.example.disadaapp.ui.Component.PredictCard
import com.example.disadaapp.ui.Component.RecommendedCard
import com.example.disadaapp.ui.Component.ResultCard
import com.github.squti.androidwaverecorder.WaveRecorder
import java.io.File


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    recorder: AudioService2,
    modifier: Modifier = Modifier,
    viewModel: AudioViewModel = hiltViewModel()
) {
    var result by remember { mutableStateOf("") }

    var resultRecommended by remember { mutableStateOf("") }
    var resultExplaination by remember { mutableStateOf("") }

    var probabilities1 by remember { mutableStateOf("") }
    var probabilities2 by remember { mutableStateOf("") }
    var probabilities3 by remember { mutableStateOf("") }
    var probabilities4 by remember { mutableStateOf("") }
    var probabilities5 by remember { mutableStateOf("") }

    var audioFile: File? = null
    val cacheDir = LocalContext.current.cacheDir
    val filePath: String = cacheDir.absolutePath + "/temp_audio.wav"
    val waveRecorder = WaveRecorder(filePath)
    val mediaRecorder by remember { mutableStateOf<AudioService?>(null) }
    val playerRecorder by remember { mutableStateOf<AudioService?>(null) }
    var isRecording by remember { mutableStateOf(false) }
    
    viewModel.hasil.collectAsState(initial = UiState.Loading).value.let { 
        when(it) {
            is UiState.Loading -> {
                
            }
            is UiState.Success<*> -> {
                ResultCard(
                    result = result
                )
            }
            is UiState.Error -> {
                
            }
        }
    }

    viewModel.kemungkinan.collectAsState(initial = UiState.Loading).value.let {
        when(it) {
            is UiState.Loading -> {
                //ButtonCustomRecord(onClick = { audioFile?.let { it1 -> viewModel.predictAudio(it1) } }, modifier = modifier)
            }
            is UiState.Success<*> -> {
                PredictCard(
                    value1 = probabilities1.toInt(),
                    value2 = probabilities2.toInt(),
                    value3 = probabilities3.toInt(),
                    value4 = probabilities4.toInt(),
                    value5 = probabilities5.toInt(),
                )
            }
            is UiState.Error -> {

            }
        }
    }

    viewModel.rekomendasiPanganan.collectAsState(initial = UiState.Loading).value.let {
        when(it) {
            is UiState.Loading -> {
                //viewModel.predictAudio(audioFile!!)

            }
            is UiState.Success<*> -> {
                RecommendedCard(
                    recomValue = resultRecommended,
                    expValue = resultExplaination)
            }
            is UiState.Error -> {

            }
        }
    }

    probabilities1 = viewModel.kemungkinan.value?.merasaKesakitan.hashCode().toString()
    probabilities2 = viewModel.kemungkinan.value?.merasaKurangNyaman.hashCode().toString()
    probabilities3 = viewModel.kemungkinan.value?.sedangLapar.hashCode().toString()
    probabilities4 = viewModel.kemungkinan.value?.sedangLelah.hashCode().toString()
    probabilities5 = viewModel.kemungkinan.value?.sedangMerasaKembung.hashCode().toString()

    resultRecommended = viewModel.rekomendasiPanganan.value?.rekomendasi.toString()
    resultExplaination = viewModel.rekomendasiPanganan.value?.penjelasan.toString()

    result = viewModel.hasil.value.toString()
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
                CardCustom(
                    modifier = modifier,
                    recomValue = resultRecommended,
                    expValue = resultExplaination,
                    value1 = probabilities1.toDouble().toInt(),
                    value2 = probabilities2.toDouble().toInt(),
                    value3 = probabilities3.toDouble().toInt(),
                    value4 = probabilities4.toDouble().toInt(),
                    value5 = probabilities5.toDouble().toInt(),
                    result = result
                )
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
//                        recorder.stopRecorder()
                        waveRecorder.stopRecording()
                        File(cacheDir, "temp_audio.wav").also {
                            audioFile = it
                            viewModel.predictAudio(it)
                        }
//                        mediaRecorder?.stopRecorder()
                        isRecording = false
                    } else {
                        // Memulai perekaman jika tidak sedang merekam
                        waveRecorder.startRecording()
                        isRecording = true
//                        File(cacheDir, "temp_audio.wav").also {
//                            recorder.startRecorder(it)
////                            mediaRecorder?.startRecorder(it)
//                            audioFile = it
//                            isRecording = true
//                            viewModel.predictAudio(audioFile!!)
//
//                        }
                    }
                },
                modifier = Modifier.background(
                    if (isRecording) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary))
//            ButtonCustomRecord(
//                onClick = {
//                    if (isRecording) {
//                        recorder.stopRecorder()
////                        mediaRecorder?.stopRecorder()
//                        isRecording = false
//                    } else {
//                        // Memulai perekaman jika tidak sedang merekam
//                        File(cacheDir, "temp_audio.wav").also {
//                            recorder.startRecorder(it)
////                            mediaRecorder?.startRecorder(it)
//                            audioFile = it
//                            isRecording = true
//                            viewModel.predictAudio(audioFile!!)
//
//                        }
//                    }
//                },
//                modifier = Modifier.background(
//                    if (isRecording) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
//                )
//            )

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
