package com.example.disadaapp.data.model

import com.google.gson.annotations.SerializedName


data class AudioData(
    @field:SerializedName("audio_content")
    val audioContent: ByteArray
)
