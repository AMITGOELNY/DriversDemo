package com.goel.riderapp.di

import com.goel.riderapp.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/"

val networkModule = module {
    single { provideRetrofitConverterFactory() }

    single { provideDefaultHttpClient() }

    single { provideRetrofit(okHttpClient = get(), factory = get()) }
}

private fun provideRetrofitConverterFactory(): Converter.Factory {
    val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
    }
    return json.asConverterFactory("application/json".toMediaType())
}

private fun provideDefaultHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .callTimeout(60L, TimeUnit.SECONDS)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
                )
            }
        }
        .build()
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    factory: Factory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(factory)
        .client(okHttpClient)
        .build()
}
