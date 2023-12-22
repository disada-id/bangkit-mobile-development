package com.example.disadaapp.Utils

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream

class AudioService2(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    private var mediaRecorder: MediaRecorder? = null

    private fun createRecorder(): MediaRecorder {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else
            @Suppress("deprecation")
            (MediaRecorder())
    }

    //media recorder
    fun startRecorder(outputFile: File) {
        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setMaxDuration(6000) // max duration 7 seconds
            setOutputFile(FileOutputStream(outputFile).fd)
            setAudioEncodingBitRate(16 * 44100)
            setAudioSamplingRate(96000)

            setOnInfoListener { mr, what, extra ->
                if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                    Log.v("AUDIORECORDER", "Maximum Duration Reached")
                    Toast.makeText(context, "Recording stopped", Toast.LENGTH_SHORT).show()
                    stopRecorder()
                }
            }
            mediaRecorder?.apply {
                prepare()
                start()
            }
            mediaRecorder = this
            Toast.makeText(context, "Start Recording...", Toast.LENGTH_SHORT).show()

            try {
                mediaRecorder?.apply {
                    prepare()
                    start()
                }
                mediaRecorder = this
                Toast.makeText(context, "Start Recording...", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("AUDIORECORDER", "Error preparing or starting recording: ${e.message}")
                // Handle the exception as needed
            }

        }

    }

    fun stopRecorder() {
        try {
            mediaRecorder?.apply {
                stop()
                release()
            }
            mediaRecorder = null
//            Toast.makeText(context, "Recording stopped", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("AUDIORECORDER", "Error stopping or releasing recorder: ${e.message}")
            // Handle the exception as needed
        }
    }

    //media player
    fun play(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            mediaPlayer = this
            mediaPlayer?.start()
        }
    }
    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}