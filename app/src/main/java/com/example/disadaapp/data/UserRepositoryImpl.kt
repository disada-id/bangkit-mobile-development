package com.example.disadaapp.data

import com.example.disadaapp.UiState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : UserRepository {
    override fun register(
        name: String,
        email: String,
        password: String,
        noTelp: Int
    ): Flow<UiState<AuthResult>> {
        return flow {
            emit(UiState.Loading)
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password)
        }
    }
}