package com.example.disadaapp.ui.Component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disadaapp.ui.theme.DisadaAppTheme
import com.example.disadaapp.ui.theme.GrayBlue
import com.example.disadaapp.ui.theme.Pink80
import com.example.disadaapp.ui.theme.poppinsFamily

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CardCustom(
    modifier: Modifier = Modifier,
    recomValue: String,
    expValue: String,
    result: String,
    value1: Int,
    value2: Int,
    value3: Int,
    value4: Int,
    value5: Int,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(GrayBlue),
    ) {
        PredictCard(
            modifier = Modifier,
            value1 = value1, value2 = value2, value3 = value3, value4 = value4, value5 = value5,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                ResultCard(result = result)
            }
            RecommendedCard(
                recomValue = recomValue,
                expValue = expValue
            )
        }
    }
}

@Composable
fun ResultCard(
    modifier: Modifier = Modifier,
    result: String
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        item {
            Text(
                modifier = Modifier.padding(10.dp),
                text = result,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                fontFamily = poppinsFamily
            )
        }
    }

}

@Composable
fun RecommendedCard(
    recomValue: String,
    expValue: String,
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        item{
            Text(
                text = expValue,
                color = Color.Black,
                fontFamily = poppinsFamily,)
        }
        item{
            Text(text = recomValue,
                color = Color.Black,
                fontFamily = poppinsFamily)
        }
    }

}

@Composable
fun PredictCard(
    modifier: Modifier = Modifier,
    value1: Int,
    value2: Int,
    value3: Int,
    value4: Int,
    value5: Int,
) {
    Card(modifier = Modifier
        .clip(MaterialTheme.shapes.medium)
        .fillMaxWidth()
        .height(180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(Pink80))
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),

            ) {
            val listState = rememberLazyListState()
            LazyColumn(state = listState) {
                item {
                    Text(
                        text = "Bayi Merasa Kesakitan",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFamily,
                    )
                    ProgressBarCustom(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(11.dp),
                        width = 350.dp,
                        backgroundColor = Color.White,
                        foregroundColor = Brush.horizontalGradient(
                            listOf(
                                Color(0xFFCC3C3C),
                                Color(0xFFCA6363)
                            )
                        ),
                        percent = value1.toInt(),
                        isTextShown = true
                    )
                }
                item {
                    Text(
                        text = "Bayi Sedang Lapar",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFamily,
                    )
                    ProgressBarCustom(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(11.dp),
                        width = 350.dp,
                        backgroundColor = Color.White,
                        foregroundColor = Brush.horizontalGradient(
                            listOf(
                                Color(0xFFCC3C3C),
                                Color(0xFFCA6363)
                            )
                        ),
                        percent = value2.toInt(),
                        isTextShown = true
                    )
                }
                item {
                    Text(
                        text = "Bayi Sedang Lelah",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFamily,
                    )
                    ProgressBarCustom(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(11.dp),
                        width = 350.dp,
                        backgroundColor = Color.White,
                        foregroundColor = Brush.horizontalGradient(
                            listOf(
                                Color(0xFFCC3C3C),
                                Color(0xFFCA6363)
                            )
                        ),
                        percent = value3.toInt(),
                        isTextShown = true
                    )
                }
                item {
                    Text(
                        text = "Bayi Sedang Merasa Kembung",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFamily,
                    )
                    ProgressBarCustom(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(11.dp),
                        width = 350.dp,
                        backgroundColor = Color.White,
                        foregroundColor = Brush.horizontalGradient(
                            listOf(
                                Color(0xFFCC3C3C),
                                Color(0xFFCA6363)
                            )
                        ),
                        percent = value4.toInt(),
                        isTextShown = true
                    )
                }
                item {
                    Text(
                        text = "Bayi Merasa Kurang Nyaman",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFamily,
                    )
                    ProgressBarCustom(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(11.dp),
                        width = 350.dp,
                        backgroundColor = Color.White,
                        foregroundColor = Brush.horizontalGradient(
                            listOf(
                                Color(0xFFCC3C3C),
                                Color(0xFFCA6363)
                            )
                        ),
                        percent = value5.toInt(),
                        isTextShown = true,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    DisadaAppTheme {
        CardCustom(
            value1 = 89,
            value2 = 44,
            value3 = 9099,
            value4 = 76,
            value5 = 89,
            expValue = "",
            recomValue = "",
            result = "",
        )
    }
}