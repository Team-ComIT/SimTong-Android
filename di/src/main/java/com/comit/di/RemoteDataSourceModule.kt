@file:Suppress("UnnecessaryAbstractClass")

package com.comit.di

import com.comit.data.datasource.RemoteAuthDataSource
import com.comit.data.datasource.RemoteFileDataSource
import com.comit.remote.datasource.RemoteAuthDataSourceImpl
import com.comit.remote.datasource.RemoteFileDataSourceImpl
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
    abstract fun provideRemoteFileDataSource(
        remoteFileDataSourceImpl: RemoteFileDataSourceImpl
    ): RemoteFileDataSource
}
