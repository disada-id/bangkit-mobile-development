package com.example.disadaapp.Utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disadaapp.data.DisadaRepository
import com.example.disadaapp.data.network.ApiService
import com.example.disadaapp.data.respone.Kemungkinan
import com.example.disadaapp.data.respone.RekomendasiPanganan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
                .collect {
                    _hasil.value = it.toString()
                    _kemungkinan.value?.merasaKesakitan = it.toString()
                    //_rekomendasiPanganan.value = it.toString()
                }
        }
    }
}
    //untuk mengubah desimal menjadi integer
    fun calculateKemungkinan(result: Any): Kemungkinan? {
        return when (result) {
            is Double -> {
                // Contoh: Jika tipe data result adalah Double
                val kemungkinan = result * 100
                Kemungkinan(
                    "kesakitan: $kemungkinan%",
                    "sedangLapar: $kemungkinan%",
                    "sedangLelah: $kemungkinan%",
                    "sedangKembung: $kemungkinan%",
                    "sedangKurangNyaman: $kemungkinan%")
            }
            else -> {
                // Contoh: Tipe data lainnya
                Kemungkinan("Tipe data tidak dikenali")
            }
        }
    }

//            try {
//                val result = repository.postAudio(file).first() // Use 'first' to get the first emitted item
//                _hasil.value = result.toString()
//                _kemungkinan.value = calculateKemungkinan(result) // You need to implement this function
//                //_rekomendasiPanganan.value = generateRekomendasiPanganan(result) // You need to implement this function
//            } catch (e: Exception) {
//                // Handle exceptions if any
//                // You might want to set an error state or show a message to the user
//                _hasil.value = "Error processing audio: ${e.message}"
//                _kemungkinan.value = ""
//                _rekomendasiPanganan.value = ""

//    fun predictAudio(file: File) {
//        viewModelScope.launch {
//            repository.postAudio(file)
//                .collect {
//                    _hasil.value = it.toString()
//                    _kemungkinan.value =
//                    _rekomendasiPanganan.value = it.toString()
//                }
//        }
//    }
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
//                        }
//
//                        is ApiResponse.Error -> {
//                            // Handling error response if needed
//                            Log.e("GET PREDICT", "Error: ${apiResponse.errorMessage}")
//                            // You may want to update UI states or show an error message
//                        }
//                    }
//                }

    //suspend fun postAudio(file: File) = repository.postAudio(file)
