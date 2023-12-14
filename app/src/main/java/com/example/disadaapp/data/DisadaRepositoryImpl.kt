package com.example.disadaapp.data

import com.example.disadaapp.UiState
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DisadaRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth, ) : DisadaRepository {

    override fun googleSignIn(credential: AuthCredential): Flow<UiState<AuthResult>> {
        return flow {
            emit(UiState.Loading)
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(UiState.Success(result))
        }.catch {
            emit(UiState.Error(it.message.toString()))
        }
    }

}