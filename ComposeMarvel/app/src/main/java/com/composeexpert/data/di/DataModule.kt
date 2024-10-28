package com.composeexpert.data.di

import com.composeexpert.data.network.remote.CharactersService
import com.composeexpert.data.network.remote.ComicsService
import com.composeexpert.data.network.remote.EventsService
import com.composeexpert.data.network.remote.QueryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @ApiEndpoint
    fun provideApiEndpoint() : String = "https://gateway.marvel.com/"

    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        queryInterceptor: QueryInterceptor,
    ) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(queryInterceptor)
        .build()

    @Provides
    fun providerRestAdapter(@ApiEndpoint apiEndpoint: String, httpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl(apiEndpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    @Provides
    fun provideCharactersService(restAdapter: Retrofit) : CharactersService = restAdapter.create()

    @Provides
    fun provideComicsService(restAdapter: Retrofit) : ComicsService = restAdapter.create()
    @Provides
    fun provideEventsService(restAdapter: Retrofit) : EventsService = restAdapter.create()
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint