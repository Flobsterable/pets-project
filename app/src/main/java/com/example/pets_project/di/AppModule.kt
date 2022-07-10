package com.example.pets_project.di

import com.example.pets_project.repository.Repository
import com.example.pets_project.repository.RepositoryImpl
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.services.network.NetworkServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNetwork() : NetworkService = NetworkServiceImpl()

    @Singleton
    @Provides
    fun provideRepository(networkService: NetworkService) : Repository = RepositoryImpl(networkService)
}