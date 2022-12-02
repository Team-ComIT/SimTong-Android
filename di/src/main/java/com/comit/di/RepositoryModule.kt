@file:Suppress("UnnecessaryAbstractClass")

package com.comit.di

import com.comit.data.repository.AuthRepositoryImpl
import com.comit.data.repository.CommonsRepositoryImpl
import com.comit.data.repository.MenuRepositoryImpl
import com.comit.domain.repository.AuthRepository
import com.comit.domain.repository.CommonsRepository
import com.comit.domain.repository.MenuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun provideCommonsRepository(
        commonsRepositoryImpl: CommonsRepositoryImpl,
    ): CommonsRepository

    @Singleton
    @Binds
    abstract fun provideMenuRepository(
        menuRepositoryImpl: MenuRepositoryImpl,
    ): MenuRepository
}
