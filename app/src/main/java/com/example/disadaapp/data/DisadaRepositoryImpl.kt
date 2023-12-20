package com.example.disadaapp.data

import com.example.disadaapp.UiState
import com.example.disadaapp.data.model.AudioData
import com.example.disadaapp.data.network.ApiResponse
import com.example.disadaapp.data.network.ApiService
import com.example.disadaapp.data.respone.PredictsResponse
import com.example.disadaapp.data.respone.SigninResponse
import com.example.disadaapp.data.respone.SignupResponse
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DisadaRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val apiService: ApiService
    ) : DisadaRepository {

    override fun googleSignIn(credential: AuthCredential): Flow<UiState<AuthResult>> {
        return flow {
            emit(UiState.Loading)
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(UiState.Success(result))
        }.catch {
            emit(UiState.Error(it.message.toString()))
        }
    }

    override fun signup(
        email: String,
        password: String,
        username: String,
        fullname: String,
        nohp: String
    ): Flow<ApiResponse<SignupResponse>> {
        return flow {
            val response = apiService.signup(email, password, username, fullname, nohp)
            emit(ApiResponse.Success(response))
        }.catch { e ->
            ApiResponse.Error(e.message.toString())
        }
    }

    override fun signin(email: String, password: String): Flow<ApiResponse<SigninResponse>> {
        return flow {
            val response = apiService.signin(email, password)
            emit(ApiResponse.Success(response))
        }.catch { e ->
            ApiResponse.Error(e.message.toString())
        }
    }

    override suspend fun postAudio(audioFile: File): Flow<ApiResponse<PredictsResponse>> {
        return flow {
            val requestAudioFile = audioFile.asRequestBody("audio/wav".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                    "audio",
                    audioFile.name,
                     requestAudioFile
            )
            val response = apiService.postAudio(multipartBody)
            emit(ApiResponse.Success(response))
        }.catch { e ->
            ApiResponse.Error(e.message.toString())
        }
    }

}