package com.comit.di

import android.util.Log
import com.comit.data.interceptor.AuthorizationInterceptor
import com.comit.data.interceptor.EmptyBodyInterceptor
import com.comit.remote.api.AuthAPI
import com.comit.remote.api.CommonsAPI
import com.comit.remote.api.EmailAPI
import com.comit.remote.api.FilesAPI
import com.comit.remote.api.HolidayAPI
import com.comit.remote.api.MenuAPI
import com.comit.remote.api.ScheduleAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "https://simtong-dev.comit.or.kr/"

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Log.v("HTTP", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor,
        emptyBodyInterceptor: EmptyBodyInterceptor,
    ): OkHttpClient = synchronized(
        lock = this,
    ) {
        OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(emptyBodyInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = synchronized(
        lock = this,
    ) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthAPI =
        retrofit.create(AuthAPI::class.java)

    @Provides
    fun provideCommonsAPI(retrofit: Retrofit): CommonsAPI =
        retrofit.create(CommonsAPI::class.java)

    @Provides
    fun provideEmailAPI(retrofit: Retrofit): EmailAPI =
        retrofit.create(EmailAPI::class.java)

    @Provides
    fun provideFilesAPI(retrofit: Retrofit): FilesAPI =
        retrofit.create(FilesAPI::class.java)

    @Provides
    fun provideHolidayAPI(retrofit: Retrofit): HolidayAPI =
        retrofit.create(HolidayAPI::class.java)

    @Provides
    fun provideMenuAPI(retrofit: Retrofit): MenuAPI =
        retrofit.create(MenuAPI::class.java)

    @Provides
    fun provideScheduleAPI(retrofit: Retrofit): ScheduleAPI =
        retrofit.create(ScheduleAPI::class.java)
}
