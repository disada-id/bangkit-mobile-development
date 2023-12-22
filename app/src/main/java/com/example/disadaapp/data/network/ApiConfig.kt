package com.example.disadaapp.data.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
<<<<<<< HEAD
import dagger.Provides
=======
>>>>>>> c63b5496e09491a0f6c4005fc20d020d683c2e42
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object{
        fun getApiService(@ApplicationContext context: Context): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ChuckerInterceptor(context))
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://disada-flask-service-ctlb7v5egq-et.a.run.app/")
<<<<<<< HEAD
=======
//                .baseUrl("https://disada-backend-cc-ctlb7v5egq-et.a.run.app/auth/signup")
>>>>>>> c63b5496e09491a0f6c4005fc20d020d683c2e42
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}