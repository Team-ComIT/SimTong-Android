package com.comit.di

import com.comit.local.preference.AuthPreference
import com.comit.local.preference.AuthPreferenceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Singleton
    @Provides
    abstract fun provideAuthPreference(
        authPreferenceImpl: AuthPreferenceImpl
    ): AuthPreference
}
