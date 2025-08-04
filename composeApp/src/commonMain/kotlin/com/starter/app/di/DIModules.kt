package com.starter.app.di

import com.starter.app.core.data.remote.HttpClientFactory
import com.starter.app.data.config.DefaultNetworkConfig
import com.starter.app.data.config.NetworkConfig
import org.koin.core.module.Module
import com.starter.app.domain.repository.ContributorRepository
import com.starter.app.core.util.Preferences
import com.starter.app.domain.datasource.ContributorLocalDataSource
import com.starter.app.data.datasource.local.ContributorLocalDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import  com.starter.app.domain.datasource.ContributorsRemoteDataSource
import  com.starter.app.data.datasource.remote.*
import com.starter.app.data.repository.ContributorRepositoryImp
import com.starter.app.presentation.screens.contributor.ContributorsViewModel
import com.starter.app.domain.usecase.*
import com.russhwolf.settings.Settings
import com.starter.app.core.data.local.CreateRoomDataBase
import com.starter.app.core.data.local.RoomSampleDataBase
import kotlinx.coroutines.Dispatchers

/**
 * expect for platform module to get platform specific modules
 */
expect val platformModule: Module

/**
 * Shared module using Koin
 */
val sharedModule = module {

    //Network Configuration
    single<NetworkConfig> { DefaultNetworkConfig() }

    //Preferences
    single { Preferences(Settings()) }

    //Network binding
    single { HttpClientFactory.create(get()) }

    /**
     * DataBase
     */
    single<RoomSampleDataBase> {
        CreateRoomDataBase(get()).getDataBase()
    }

    //Contributors
    singleOf(::ContributorsViewModel)
    singleOf(::GetLocalContributorsUseCase)
    singleOf(::GetRemoteContributorsUseCase)
    single<ContributorLocalDataSource> { ContributorLocalDataSourceImpl(get()) }
    single<ContributorsRemoteDataSource> {
        ContributorsRemoteDataSourceImp(get(), get())
    }
    single<ContributorRepository> { ContributorRepositoryImp(get(), get()) }

}