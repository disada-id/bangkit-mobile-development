package com.example.disadaapp.data.network

import com.example.disadaapp.data.model.AudioData
import com.example.disadaapp.data.respone.PredictResponse
import com.example.disadaapp.data.respone.PredictsResponse
import com.example.disadaapp.data.respone.SigninResponse
import com.example.disadaapp.data.respone.SignupResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("signin")
    suspend fun signin(
        @Field("email") email: String,
        @Field("password") password: String
    ): SigninResponse

    @FormUrlEncoded
    @POST("signup")
        suspend fun signup(
            @Field("email") email: String,
            @Field("password") password: String,
            @Field("username") username: String,
            @Field("fullname") fullname : String,
            @Field("nohp") nohp : String
        ): SignupResponse

        @Multipart
        @POST("predict")
        suspend fun postAudio(@Part file: MultipartBody.Part): PredictsResponse
}