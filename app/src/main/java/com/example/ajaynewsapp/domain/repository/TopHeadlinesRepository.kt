package com.example.ajaynewsapp.domain.repository

import com.example.ajaynewsapp.data.api.Article

interface TopHeadlinesRepository {
    suspend fun getTopHeadlines(): List<Article>
}
