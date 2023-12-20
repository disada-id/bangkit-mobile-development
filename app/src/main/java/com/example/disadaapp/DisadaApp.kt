package com.example.disadaapp

import androidx.compose.runtime.Composable
import com.example.disadaapp.Utils.AudioService2
import com.example.disadaapp.navigation.MyNavDrawer

@Composable
fun DisadaApp(recorder: AudioService2) {
    MyNavDrawer(recorder)
}