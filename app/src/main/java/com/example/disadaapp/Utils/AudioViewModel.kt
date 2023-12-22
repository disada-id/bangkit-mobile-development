package com.example.disadaapp.Utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disadaapp.UiState
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

    fun predictAudio(file: File) {
        // Memulai proses prediksi audio
        viewModelScope.launch {
            repository.postAudio(file)
                .collect {
                    // Lakukan operasi prediksi di sini
                    val result = repository.postAudio(file)

                    // Simulasikan hasil prediksi sukses
                    _hasil.value = UiState.Success(result).toString()

                    // Simulasikan data kemungkinan
                    val kemungkinanData = Kemungkinan(/* ... */)
                    _kemungkinan.value = kemungkinanData

                    // Simulasikan data rekomendasi panganan
                    val rekomendasiData = RekomendasiPanganan(/* ... */)
                    _rekomendasiPanganan.value = rekomendasiData
                }.runCatching { ->
                    // Handle kesalahan jika terjadi
                    _hasil.value = null
                    _kemungkinan.value = null
                    _rekomendasiPanganan.value = null
                }
        }
    }
}

        // Fungsi untuk melakukan prediksi berdasarkan audioData
//    fun predictAudio(file: File) {
//        viewModelScope.launch {
//            repository.postAudio(file)
//                .collect {
//                    _hasil.value = it.toString()
//                    _kemungkinan.value = calculateKemungkinan(_kemungkinan.value)
////                    _rekomendasiPanganan.value = it.toString()
//                }
//        }
//    }
//
//    fun calculateKemungkinan(result: Any): Kemungkinan? {
//        return when (result) {
//            is Double -> {
//                // Contoh: Jika tipe data result adalah Double
//                val kemungkinan = result * 100
//                Kemungkinan("Kemungkinan: $kemungkinan%")
//            }
//            is String -> {
//                // Contoh: Jika tipe data result adalah String
//                Kemungkinan("Hasil: $result")
//            }
//            else -> {
//                // Contoh: Tipe data lainnya
//                Kemungkinan("Tipe data tidak dikenali")
//            }
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

//     suspend fun getAudio(kemungkinan: Kemungkinan, rekomendasiPanganan: RekomendasiPanganan, hasil: String){
//         repository.getAudioResponse(kemungkinan, rekomendasiPanganan, hasil)
//    }
