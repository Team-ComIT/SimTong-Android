package com.comit.di

import android.content.Context
import com.comit.local.preference.AuthPreferenceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideContext {

    @Singleton
    @Provides
    fun provideAuthPreference(
        @ApplicationContext context: Context,
    ) = AuthPreferenceImpl(
        context = context,
    )
}
