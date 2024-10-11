package com.composeexpert.data.network

import com.composeexpert.data.network.remote.CharactersService
import com.composeexpert.data.network.remote.ComicsService
import com.composeexpert.data.network.remote.EventsService
import com.example.composeexpert.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.Date

const val API_ENDPOINT = "https://gateway.marvel.com/"

object ApiClient {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(QueryInterceptor())
        .build()
    private val restAdapter = Retrofit.Builder()
        .baseUrl(API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    val charactersService: CharactersService = restAdapter.create()
    val comicsService: ComicsService = restAdapter.create()
    val eventsService: EventsService = restAdapter.create()
}
private class QueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url
        val ts = Date().time
        val hash = generateHash(ts, BuildConfig.MARVEL_PRIVATE_KEY, BuildConfig.MARVEL_PUBLIC_KEY)
        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()
        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}