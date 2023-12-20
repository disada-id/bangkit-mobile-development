package com.example.disadaapp.ui.screen.homepage

import android.content.ClipData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.disadaapp.Utils.AudioViewModel
import com.example.disadaapp.data.model.AudioData
import com.example.disadaapp.ui.Component.ButtonCustomRecord
import com.example.disadaapp.ui.Component.CardCustom
import com.example.disadaapp.ui.Component.PredictCard
import com.example.disadaapp.ui.Component.ProgressBarCustom
import com.example.disadaapp.ui.screen.register.RegisterScreen
import com.example.disadaapp.ui.theme.DisadaAppTheme
import com.example.disadaapp.ui.theme.LightOrange


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: AudioViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

    ) {
        Column(
            modifier = modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box {
                CardCustom(modifier = modifier)
            }
            Spacer(modifier = modifier.height(10.dp))
            ButtonCustomRecord(
                onClick = {
                        viewModel.predictAudio(audioData = AudioData(ByteArray(10)))
                })
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