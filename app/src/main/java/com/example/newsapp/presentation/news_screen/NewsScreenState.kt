package com.example.newsapp.presentation.news_screen

import com.example.newsapp.data.dto.Article

data class NewsScreenState(
    val isLoading : Boolean = false,
    val articles: List<Article> = emptyList(),
    val isSearchBarVisible : Boolean = false,
    val error : String? = null,
    val selectedArticle : Article? = null,
    val searchQuery : String = "",
    val category : String = "General"
)
