package com.example.ajaynewsapp.data.api

import com.example.ajaynewsapp.utils.NativeLibraryData
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("apiKey", NativeLibraryData().getApiKey())
            .build()

        val newRequest = original.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
