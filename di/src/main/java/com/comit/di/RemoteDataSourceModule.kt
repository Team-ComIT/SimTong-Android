@file:Suppress("UnnecessaryAbstractClass")

package com.comit.di

import com.comit.data.datasource.RemoteAuthDataSource
import com.comit.data.datasource.RemoteCommonsDataSource
import com.comit.data.datasource.RemoteMenuDataSource
import com.comit.remote.datasource.RemoteAuthDataSourceImpl
import com.comit.remote.datasource.RemoteCommonsDataSourceImpl
import com.comit.remote.datasource.RemoteMenuDataSourceImpl
import com.comit.data.datasource.RemoteEmailDataSource
import com.comit.remote.datasource.RemoteEmailDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun provideRemoteAuthDatasource(
        remoteAuthDataSourceImpl: RemoteAuthDataSourceImpl
    ): RemoteAuthDataSource

    @Singleton
    @Binds
    abstract fun provideRemoteCommonsDataSource(
        remoteCommonsDataSourceImpl: RemoteCommonsDataSourceImpl,
    ): RemoteCommonsDataSource

    @Singleton
    @Binds
    abstract fun provideMenuDataSource(
        remoteMenuDataSourceImpl: RemoteMenuDataSourceImpl,
    ): RemoteMenuDataSource
    
    @Singleton
    @Binds
    abstract fun provideRemoteEmailDataSource(
        remoteEmailDataSourceImpl: RemoteEmailDataSourceImpl,
    ): RemoteEmailDataSource
}
