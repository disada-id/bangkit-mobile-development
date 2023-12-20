package com.example.disadaapp.Utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disadaapp.data.DisadaRepository
import com.example.disadaapp.data.model.AudioData
import com.example.disadaapp.data.network.ApiResponse
import com.example.disadaapp.data.network.ApiService
import com.example.disadaapp.data.respone.Kemungkinan
import com.example.disadaapp.data.respone.RekomendasiPanganan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class AudioViewModel @Inject constructor(
    private val repository: DisadaRepository,
    private val apiService: ApiService
) : ViewModel() {

    private val _hasil = MutableStateFlow<String?>(null)
    val hasil: StateFlow<String?> = _hasil

    private val _kemungkinan = MutableStateFlow<Kemungkinan?>(null)
    val kemungkinan: StateFlow<Kemungkinan?> = _kemungkinan

    private val _rekomendasiPanganan = MutableStateFlow<RekomendasiPanganan?>(null)
    val rekomendasiPanganan: StateFlow<RekomendasiPanganan?> = _rekomendasiPanganan

    // Fungsi untuk melakukan prediksi berdasarkan audioData
    fun predictAudio(file: File) {
        viewModelScope.launch {
            repository.postAudio(file)
                .onStart {
                    // masih bingung disini ditaruh apa
                }
                .collect { apiResponse ->
                    when (apiResponse) {
                        is ApiResponse.Empty -> {

                        }

                        is ApiResponse.Success -> {
                            // Mengupdate StateFlow sesuai dengan respons API
                            _hasil.value = apiResponse.data?.hasil
                            _kemungkinan.value = apiResponse.data?.kemungkinan
                            _rekomendasiPanganan.value = apiResponse.data?.rekomendasiPanganan

                        }

                        is ApiResponse.Error -> {
                            // Menangani kesalahan jika diperlukan
                        }

                        else -> {}
                    }
                }

        }
    }

    suspend fun postAudio(file: File) = repository.postAudio(file)
}