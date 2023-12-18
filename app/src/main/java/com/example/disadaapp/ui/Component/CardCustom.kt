package com.example.disadaapp.ui.Component

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disadaapp.ui.theme.DisadaAppTheme
import com.example.disadaapp.ui.theme.DullPink
import com.example.disadaapp.ui.theme.Pink80

@Composable
fun CardCustom(
    modifier: Modifier = Modifier,
    ) {
    val scroll = rememberScrollState(0)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(Pink80),
    ) {
        PredictCard()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Text( text = "Recommendation Result kdjhfaisfhsoifwfhowiefhdsaoffffffffcdniudaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaannnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnggggggggggggggggggggggggggggggggggggggggggggggggrrrrrrrrrrrrrrrrrrrrrrrrrrrrrwwwwwwwwwwwwwwwwwwwwwwwww",
                color = Color.Black,
                modifier = Modifier.verticalScroll(scroll)
            )
        }
    }
}

@Composable
fun PredictCard(
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier
        .clip(MaterialTheme.shapes.medium)
        .fillMaxWidth()
        .height(170.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(DullPink))
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier
        ) {
            Text(
                text = "HUNGRY",
                fontSize = 10.sp
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xF0288D21),
                        Color(0xF055CA4D)
                    )
                ),
                percent = 20,
                isTextShown = true
            )

            Text(
                text = "HUNGRY",
                fontSize = 10.sp
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xF0288D21),
                        Color(0xF055CA4D)
                    )
                ),
                percent = 20,
                isTextShown = true
            )

            Text(
                text = "HUNGRY",
                fontSize = 10.sp
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xF0288D21),
                        Color(0xF055CA4D)
                    )
                ),
                percent = 20,
                isTextShown = true
            )

            Text(
                text = "HUNGRY",
                fontSize = 10.sp
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xF0288D21),
                        Color(0xF055CA4D)
                    )
                ),
                percent = 20,
                isTextShown = true
            )

            Text(
                text = "HUNGRY",
                fontSize = 10.sp
            )
            ProgressBarCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(11.dp),
                width = 300.dp,
                backgroundColor = Color.Gray,
                foregroundColor = Brush.horizontalGradient(
                    listOf(
                        Color(0xF0288D21),
                        Color(0xF055CA4D)
                    )
                ),
                percent = 20,
                isTextShown = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    DisadaAppTheme {
        CardCustom()
    }
}