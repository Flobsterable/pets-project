package com.example.pets_project.di

import android.content.Context
import com.example.pets_project.navigation.AppNavigation
import com.example.pets_project.navigation.AppNavigationImpl
import com.example.pets_project.repository.Repository
import com.example.pets_project.repository.RepositoryImpl
import com.example.pets_project.services.location.LocationService
import com.example.pets_project.services.location.LocationServiceImpl
import com.example.pets_project.services.modelParser.ModelParser
import com.example.pets_project.services.modelParser.ModelParserImpl
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.services.network.NetworkServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNetwork(
        modelParser: ModelParser
    ): NetworkService = NetworkServiceImpl(modelParser)

    @Singleton
    @Provides
    fun provideLocationService(
        @ApplicationContext
        appContext: Context,
    ): LocationService = LocationServiceImpl(appContext)

    @Singleton
    @Provides
    fun provideNavigation(): AppNavigation = AppNavigationImpl()

    @Singleton
    @Provides
    fun provideRepository(
        @ApplicationContext
        appContext: Context,
    ): Repository = RepositoryImpl(appContext)

    @Singleton
    @Provides
    fun provideModelParser(
        @ApplicationContext
        appContext: Context,
    ): ModelParser = ModelParserImpl(appContext)
}
