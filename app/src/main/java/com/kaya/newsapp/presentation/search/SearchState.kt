package com.kaya.newsapp.presentation.search

import com.kaya.newsapp.domain.models.Article

data class SearchState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
)
