package com.example.disadaapp.data

import com.example.disadaapp.UiState
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface DisadaRepository {

    //fun register(name: String, email: String, password: String, noTelp: Int): Flow<UiState<AuthResult>>

    fun googleSignIn(credential: AuthCredential): Flow<UiState<AuthResult>>
}