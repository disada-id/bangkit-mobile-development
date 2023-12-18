package com.example.disadaapp.ui.Component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.disadaapp.R

@Composable
fun ButtonCustomRecord(
    onClick: () -> Unit,
    contentDescription: String? = null
) {

        Image(
            painter = painterResource(id = R.drawable.btn_record),
            contentDescription = contentDescription,
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .clickable(onClick = onClick)// Ubah ukuran ikon sesuai keinginan
        )

}


@Preview(showBackground = false)
@Composable
fun ButtonThemePreview(){
    ButtonCustomRecord(onClick = { /*TODO*/ })
}