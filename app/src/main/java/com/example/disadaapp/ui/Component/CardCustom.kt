package com.example.disadaapp.ui.Component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.disadaapp.ui.theme.DisadaAppTheme
import com.example.disadaapp.ui.theme.DullPink
import com.example.disadaapp.ui.theme.Pink40
import io.jetchart.common.animation.fadeInAnimation
import io.jetchart.pie.PieChart
import io.jetchart.pie.Pies
import io.jetchart.pie.Slice
import io.jetchart.pie.renderer.FilledSliceDrawer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCustom(
    modifier: Modifier = Modifier,
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(DullPink),
        onClick = {}
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Text( text = "Predict",
                color = Color.White
            )
        }
        Column() {

        }
    }
}

@Composable
fun DonutChartComposable() {
    PieChart(pies = Pies(listOf(Slice(30f, Red), Slice(45f, Green), Slice(15f, Yellow), Slice(5f, Cyan), Slice(5f, Pink40))),
        modifier = Modifier
            .height(50.dp)
            .padding(30.dp),
        animation = fadeInAnimation(4000),
        sliceDrawer = FilledSliceDrawer(thickness = 40f)
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    DisadaAppTheme {
        CardCustom()
    }
}
@Preview(showBackground = true)
@Composable
fun Previewchart() {
    DisadaAppTheme {
        DonutChartComposable()
    }
}