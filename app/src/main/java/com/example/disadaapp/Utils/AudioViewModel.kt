package com.example.disadaapp.Utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disadaapp.data.DisadaRepository
import com.example.disadaapp.data.network.ApiResponse
import com.example.disadaapp.data.network.ApiService
import com.example.disadaapp.data.respone.Kemungkinan
import com.example.disadaapp.data.respone.RekomendasiPanganan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
<<<<<<< HEAD
import kotlinx.coroutines.flow.collect
=======
import kotlinx.coroutines.flow.collectIndexed
>>>>>>> c63b5496e09491a0f6c4005fc20d020d683c2e42
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

<<<<<<< HEAD



    fun predictAudio(file: File){
        viewModelScope.launch {
            repository.postAudio(file)
                .collect{
                    _hasil.value = it.toString()
                }
        }
    }

    // Fungsi untuk melakukan prediksi berdasarkan audioData
//    fun predictAudio(file: File) {
//        viewModelScope.launch {
//            Log.d("YourViewModel", "Mengirim file audio ke API: ${file.absolutePath}")
//            repository.postAudio(file)
//                .onStart {
//                    // masih bingung disini ditaruh apa
//                    Log.d("YourViewModel", "Permintaan API dimulai.")
//            Log.d("SEND AUDIO", "Success send data")
//            repository.postAudio(file)
//                .onStart {
//                    // Handling UI states or operations when the API call starts
//                }
//                .catch { exception ->
//                    // Handling exceptions if the API call throws an exception
//                    Log.e("GET PREDICT", "Error: ${exception.message}")
//                }
//                .collect { apiResponse ->
//                    when (apiResponse) {
//                        is ApiResponse.Empty -> {
//                            // Handling empty response if needed
//                        }
//
//                        is ApiResponse.Success -> {
//                            Log.d("GET PREDICT", "Success receive data")
//                            // Mengupdate StateFlow sesuai dengan respons API
//                            _hasil.value = apiResponse.data?.hasil
//                            _kemungkinan.value = apiResponse.data?.kemungkinan
//                            _rekomendasiPanganan.value = apiResponse.data?.rekomendasiPanganan
//
//                            Log.d("YourViewModel", "Hasil prediksi diterima dari API.")
//
//                        }
//
//                        is ApiResponse.Error -> {
//                            // Menangani kesalahan jika diperlukan
//                            Log.e("YourViewModel", "Kesalahan dari API: ${apiResponse.errorMessage}")
//
//                        }
//
//                        else -> {}
//                        }
//
//                    }
//                }
//        }
//    }

//    fun predictAudio(file: File) {
//        viewModelScope.launch {
//            Log.d("SEND AUDIO", "success send data")
=======
    // Fungsi untuk melakukan prediksi berdasarkan audioData
    fun predictAudio(file: File) {
        viewModelScope.launch {
            repository.postAudio(file)
                .collect {
                    _hasil.value = it.toString()
//                    _kemungkinan.value = it.toString()
//                    _rekomendasiPanganan.value = it.toString()
                }
        }
    }
//            Log.d("SEND AUDIO", "Success send data")
>>>>>>> c63b5496e09491a0f6c4005fc20d020d683c2e42
//            repository.postAudio(file)
//                .onStart {
//                    // Handling UI states or operations when the API call starts
//                }
//                .catch { exception ->
//                    // Handling exceptions if the API call throws an exception
//                    Log.e("GET PREDICT", "Error: ${exception.message}")
//                }
//                .collect { apiResponse ->
//                    when (apiResponse) {
//                        is ApiResponse.Empty -> {
//                            // Handling empty response if needed
//                        }
//
//                        is ApiResponse.Success -> {
//                            Log.d("GET PREDICT", "Success receive data")
//                            // Mengupdate StateFlow sesuai dengan respons API
//                            _hasil.value = apiResponse.data?.hasil
//                            _kemungkinan.value = apiResponse.data?.kemungkinan
//                            _rekomendasiPanganan.value = apiResponse.data?.rekomendasiPanganan
//                        }
//
//                        is ApiResponse.Error -> {
//                            // Handling error response if needed
//                            Log.e("GET PREDICT", "Error: ${apiResponse.errorMessage}")
//                            // You may want to update UI states or show an error message
//                        }
//                    }
//                }

     suspend fun getAudio(kemungkinan: Kemungkinan, rekomendasiPanganan: RekomendasiPanganan, hasil: String){
         repository.getAudioResponse(kemungkinan, rekomendasiPanganan, hasil)
    }
}