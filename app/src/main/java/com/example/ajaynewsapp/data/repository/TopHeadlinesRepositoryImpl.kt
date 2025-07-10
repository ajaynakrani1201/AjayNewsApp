package com.example.ajaynewsapp.data.repository

import com.example.ajaynewsapp.data.api.Article
import com.example.ajaynewsapp.data.api.NewsApiService
import com.example.ajaynewsapp.domain.repository.TopHeadlinesRepository
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor(
    private val api: NewsApiService
) : TopHeadlinesRepository {

    override suspend fun getTopHeadlines(): List<Article> {
        return api.getTopHeadlines().articles
    }
}