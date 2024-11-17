package com.example.newsapp.presentation.news_screen

import com.example.newsapp.data.dto.Article

// This class contains all the possible actions user can perform
sealed class NewsScreenEvent {
    class OnNewsCardClick(val article: Article) : NewsScreenEvent()
    class OnCategoryChange(val category : String) : NewsScreenEvent()
    class OnSearchQueryChanged(val searchQuery : String) : NewsScreenEvent()
    object OnCloseIconClick : NewsScreenEvent()
    object OnSearchIconClick : NewsScreenEvent()
}
