package com.example.newsapp.presentation.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    var state by mutableStateOf(NewsScreenState())
        private set
    private var searchJob : Job? = null

    fun onEvent(event: NewsScreenEvent) {
        when(event) {
            is NewsScreenEvent.OnNewsCardClick -> {
                state = state.copy(selectedArticle = event.article)
            }
            is NewsScreenEvent.OnCategoryChange -> {
                state = state.copy(category = event.category)
                getNews(category = state.category)
            }
            NewsScreenEvent.OnCloseIconClick -> {
                state = state.copy(isSearchBarVisible = false)
                getNews(category = state.category)
            }
            NewsScreenEvent.OnSearchIconClick -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    articles = emptyList()
                )
            }
            is NewsScreenEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000)
                    searchNews(query = state.searchQuery)
                }
            }

        }
    }
    private fun getNews(category : String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when(val result = repository.getNews(category = category)) {
                is Resource.Success -> {
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }

            }
        }
    }

    private fun searchNews(query : String) {
        if (query.isEmpty()) {
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when(val result = repository.searchNews(query = query)) {
                is Resource.Success -> {
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }

            }
        }
    }
}