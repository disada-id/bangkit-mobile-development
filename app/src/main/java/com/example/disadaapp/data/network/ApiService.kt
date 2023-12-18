package com.example.disadaapp.data.network

import com.example.disadaapp.data.respone.SigninResponse
import com.example.disadaapp.data.respone.SignupResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
}