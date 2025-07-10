package com.example.ajaynewsapp.domain.usecase

import com.example.ajaynewsapp.data.api.Article
import com.example.ajaynewsapp.domain.repository.TopHeadlinesRepository
import javax.inject.Inject

class TopHeadlinesUseCase @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository
) {
    suspend operator fun invoke(): List<Article> = topHeadlinesRepository.getTopHeadlines()
}