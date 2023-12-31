package com.example.disadaapp.data

import com.example.disadaapp.UiState
import com.example.disadaapp.data.model.AudioData
import com.example.disadaapp.data.network.ApiResponse
import com.example.disadaapp.data.respone.Kemungkinan
import com.example.disadaapp.data.respone.PredictsResponse
import com.example.disadaapp.data.respone.RekomendasiPanganan
import com.example.disadaapp.data.respone.SigninResponse
import com.example.disadaapp.data.respone.SignupResponse
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import java.io.File

interface DisadaRepository{

    //fun register(name: String, email: String, password: String, noTelp: Int): Flow<UiState<AuthResult>>

    //firebase
    fun googleSignIn(credential: AuthCredential): Flow<UiState<AuthResult>>

    // register
    fun signup(
        email: String,
        password: String,
        username: String,
        fullname: String,
        nohp: String
    ): Flow<ApiResponse<SignupResponse>>


    //login
    fun signin(
        email: String,
        password: String
    ): Flow<ApiResponse<SigninResponse>>

    //audio
  suspend  fun postAudio(audioFile: File): Flow<ApiResponse<PredictsResponse>>

  //get
  suspend fun getAudioResponse(kemungkinan: Kemungkinan, rekomendasiPanganan: RekomendasiPanganan, hasil: String): PredictsResponse

}