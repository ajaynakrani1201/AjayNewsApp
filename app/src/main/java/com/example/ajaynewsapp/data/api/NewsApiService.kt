package com.example.ajaynewsapp.data.api

import retrofit2.http.GET

interface NewsApiService {

    @GET("everything?q=india")
    suspend fun getTopHeadlines(): NewsResponse
}