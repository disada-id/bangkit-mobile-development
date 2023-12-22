package com.example.disadaapp.di

import android.content.Context
<<<<<<< HEAD
import com.chuckerteam.chucker.api.ChuckerInterceptor
=======
>>>>>>> c63b5496e09491a0f6c4005fc20d020d683c2e42
import com.example.disadaapp.data.DisadaRepository
import com.example.disadaapp.data.DisadaRepositoryImpl
import com.example.disadaapp.data.network.ApiConfig.Companion.getApiService
import com.example.disadaapp.data.network.ApiService
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
<<<<<<< HEAD
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
=======
>>>>>>> c63b5496e09491a0f6c4005fc20d020d683c2e42
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun firebaseProvide() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideDisadaRepository(firebaseAuth: FirebaseAuth, apiService: ApiService): DisadaRepository {
        return DisadaRepositoryImpl(firebaseAuth, apiService)
    }

    @Provides
    @Singleton
<<<<<<< HEAD
    fun provideApiService(@ApplicationContext context: Context): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ChuckerInterceptor(context))
            .build()
        return Retrofit.Builder()
//            .baseUrl("https://disada-backend-cc-ctlb7v5egq-et.a.run.app/auth/")
            .baseUrl("https://disada-flask-service-ctlb7v5egq-et.a.run.app/")
//            .baseUrl("https://2802-180-253-248-48.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
=======
    fun provideApiService(): ApiService {
        return getApiService()
//        return Retrofit.Builder()
////            .baseUrl("https://disada-backend-cc-ctlb7v5egq-et.a.run.app/auth/")
//            .baseUrl("https://disada-flask-service-ctlb7v5egq-et.a.run.app/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
>>>>>>> c63b5496e09491a0f6c4005fc20d020d683c2e42
    }
}
