package com.example.disadaapp.ui.Component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.disadaapp.Utils.AudioViewModel
import com.example.disadaapp.ui.theme.DisadaAppTheme
import com.example.disadaapp.ui.theme.GrayBlue
import com.example.disadaapp.ui.theme.Pink80

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CardCustom(
    modifier: Modifier = Modifier,
    viewModel: AudioViewModel = hiltViewModel(),
    recomValue: String,
    expValue: String,
    value1: Double,
    value2: Double,
    value3: Double,
    value4: Double,
    value5: Double
    ) {
    viewModel.kemungkinan.value?.merasaKesakitan.toString()
    val scroll = rememberScrollState(0)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
//            .padding(horizontal = 40.dp, vertical = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(GrayBlue),
    ) {
        PredictCard(value1 = value1, value2 = value2, value3 = value3, value4 = value4, value5 = value5)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Text( text = recomValue,
                color = Color.Black,
                modifier = Modifier.verticalScroll(scroll)
            )
            Text( text = expValue,
                color = Color.Black,
                modifier = Modifier.verticalScroll(scroll)
            )
        }
    }
}

@Composable
fun PredictCard(
    modifier: Modifier = Modifier,
    value1: Double,
    value2: Double,
    value3: Double,
    value4: Double,
    value5: Double,
) {
    Card(modifier = Modifier
        .clip(MaterialTheme.shapes.medium)
        .fillMaxWidth()
        .height(180.dp),
        //.padding(horizontal = 40.dp, vertical = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(Pink80))
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Bayi Merasa Kesakitan",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xFFCC3C3C),
                        Color(0xFFCA6363)
                    )
                ),
                percent = value1.toInt(),
                isTextShown = true
            )

            Text(
                text = "Bayi Sedang Lapar",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xFFCC3C3C),
                        Color(0xFFCA6363)
                    )
                ),
                percent = value2.toInt(),
                isTextShown = true
            )

            Text(
                text = "Bayi Sedang Lelah",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xFFCC3C3C),
                        Color(0xFFCA6363)
                    )
                ),
                percent = value3.toInt(),
                isTextShown = true
            )

            Text(
                text = "Bayi Sedang Merasa Kembung",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xFFCC3C3C),
                        Color(0xFFCA6363)
                    )
                ),
                percent = value4.toInt(),
                isTextShown = true
            )

            Text(
                text = "Bayi Merasa Kurang Nyaman",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xFFCC3C3C),
                        Color(0xFFCA6363)
                    )
                ),
                percent = value5.toInt(),
                isTextShown = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    DisadaAppTheme {
        CardCustom(
            expValue = "",
            recomValue = "",
            value1 = 0.323,
            value2 = 0.44,
            value3 = 0.9099,
            value4 = 0.76,
            value5 = 0.89
        )
    }
}