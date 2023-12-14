package com.example.disadaapp.Utils

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disadaapp.UiState
import com.example.disadaapp.data.DisadaRepository
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: DisadaRepository) : ViewModel() {
    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

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
}