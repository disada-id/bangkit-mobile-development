package com.example.disadaapp.Utils

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disadaapp.UiState
import com.example.disadaapp.data.DisadaRepository
import com.example.disadaapp.data.model.AudioData
import com.example.disadaapp.data.network.ApiResponse
import com.example.disadaapp.data.network.ApiService
import com.example.disadaapp.data.respone.PredictResponse
import com.example.disadaapp.data.respone.PredictionProbabilities
import com.example.disadaapp.data.respone.SigninResponse
import com.example.disadaapp.data.respone.SignupResponse
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: DisadaRepository,
    private val apiService: ApiService
    ) : ViewModel() {
    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

    private val _apiState = MutableStateFlow<ApiResponse<SignupResponse>>(ApiResponse.Empty)
    val apiState: StateFlow<ApiResponse<SignupResponse>> get() = _apiState

    private val _loginState = MutableStateFlow<ApiResponse<SigninResponse>>(ApiResponse.Empty)
    val loginState: StateFlow<ApiResponse<SigninResponse>> = _loginState

   // StateFlow untuk hasil prediksi
    private val _predictedLabel = MutableStateFlow<String?>(null)
    val predictedLabel: StateFlow<String?> = _predictedLabel

    // StateFlow untuk probabilities
    private val _probabilities = MutableStateFlow<PredictionProbabilities?>(null)
    val probabilities: StateFlow<PredictionProbabilities?> = _probabilities


    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        repository.googleSignIn(credential).collect{
            when (it) {
                is UiState.Success -> {
                    _googleState.value = GoogleSignInState(success = it.data)
                }
                is UiState.Loading -> {
                    _googleState.value = GoogleSignInState(loading = true)
                }
                is UiState.Error -> {
                    _googleState.value = GoogleSignInState(error = it.errorMessage)
                }
            }
        }
    }

    // Fungsi untuk melakukan pendaftaran
    fun signup(
        email: String,
        password: String,
        username: String,
        fullname: String,
        nohp: String
    ) {
        viewModelScope.launch {
            repository.signup(email, password, username, fullname, nohp)
                .collect { response ->
                    _apiState.value = response
                }
        }
    }

    //login
    fun signin(email: String, password: String) {
        viewModelScope.launch {
            // Menggunakan repository untuk melakukan login
            repository.signin(email, password)
                .collect { response ->
                    _loginState.value = response
                }
        }
    }

    // audio

    fun predictAudio(audioData: AudioData) {
        viewModelScope.launch {
            try {
                val result = repository.postAudio(audioData)
                // Proses hasil prediksi sesuai kebutuhan Anda
                handlePredictionResult(result)
            } catch (e: Exception) {
                // Tangani kesalahan selama proses prediksi
                handlePredictionError(e)
            }
        }
    }

    // Fungsi untuk menangani hasil prediksi
    private fun handlePredictionResult(result: PredictResponse) {
        //  hasil prediksi
        val predictedLabel = result.predictedLabel
        val probabilities = result.predictionProbabilities

        // Tampilkan hasil prediksi ke konsol
        println("Hasil Prediksi: $predictedLabel")

        // Set nilai StateFlow predictedLabel
        _predictedLabel.value = result.predictedLabel

        // Set nilai StateFlow probabilities
        _probabilities.value = result.predictionProbabilities


    }

    // Fungsi untuk menangani kesalahan selama proses prediksi
    private fun handlePredictionError(error: Throwable) {
        val errorMessage = "Terjadi kesalahan saat melakukan prediksi: ${error.message}"

        println(errorMessage)
    }


}