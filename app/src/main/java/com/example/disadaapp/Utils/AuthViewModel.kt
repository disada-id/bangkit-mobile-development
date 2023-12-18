package com.example.disadaapp.Utils

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disadaapp.UiState
import com.example.disadaapp.data.DisadaRepository
import com.example.disadaapp.data.network.ApiResponse
import com.example.disadaapp.data.network.ApiService
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
    val apiState: StateFlow<ApiResponse<SignupResponse>> = _apiState

    private val _loginState = MutableStateFlow<ApiResponse<SigninResponse>>(ApiResponse.Empty)
    val loginState: StateFlow<ApiResponse<SigninResponse>> = _loginState

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

}