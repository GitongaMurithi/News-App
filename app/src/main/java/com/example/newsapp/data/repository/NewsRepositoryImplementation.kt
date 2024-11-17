package com.example.newsapp.data.repository

import com.example.newsapp.data.dto.Article
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.util.Resource
import javax.inject.Inject

class NewsRepositoryImplementation @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getNews(category: String): Resource<List<Article>> {
        return try {
            val response = newsApi.getNews(category = category)
            Resource.Success(response.articles)
        } catch (exception: Exception) {
            Resource.Error("Failed to fetch news. ${exception.message}")
        }
    }

    override suspend fun searchNews(query: String): Resource<List<Article>> {
        return try {
            val response = newsApi.searchNews(query = query)
            Resource.Success(response.articles)
        } catch (exception: Exception) {
            Resource.Error("Failed to fetch news. ${exception.message}")
        }
    }
}