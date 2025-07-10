package com.example.ajaynewsapp.di

import com.example.ajaynewsapp.data.api.ApiKeyInterceptor
import com.example.ajaynewsapp.data.api.NewsApiService
import com.example.ajaynewsapp.data.repository.TopHeadlinesRepositoryImpl
import com.example.ajaynewsapp.domain.repository.TopHeadlinesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApiKeyInterceptor(): Interceptor = ApiKeyInterceptor()

    @Provides
    fun provideOkHttpClient(apiKeyInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun provideNewsApi(okHttpClient: OkHttpClient): NewsApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}
