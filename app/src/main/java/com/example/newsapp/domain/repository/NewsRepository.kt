package com.example.newsapp.domain.repository

import com.example.newsapp.data.dto.Article
import com.example.newsapp.util.Resource

interface NewsRepository {
    suspend fun getNews(category : String) : Resource<List<Article>>
    suspend fun searchNews(query : String) : Resource<List<Article>>

}