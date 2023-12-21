package com.example.disadaapp.di

import com.example.disadaapp.data.DisadaRepository
import com.example.disadaapp.data.DisadaRepositoryImpl
import com.example.disadaapp.data.network.ApiConfig
import com.example.disadaapp.data.network.ApiService
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
//            .baseUrl("https://disada-backend-cc-ctlb7v5egq-et.a.run.app/auth/")
            .baseUrl("https://disada-flask-service-ctlb7v5egq-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}