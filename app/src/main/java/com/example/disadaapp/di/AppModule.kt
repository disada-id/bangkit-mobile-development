package com.example.disadaapp.di

import com.example.disadaapp.data.DisadaRepository
import com.example.disadaapp.data.DisadaRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun firebaseProvide() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideDisadaRepository(firebaseAuth: FirebaseAuth): DisadaRepository {
        return DisadaRepositoryImpl(firebaseAuth)
    }
}