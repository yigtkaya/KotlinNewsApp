package com.kaya.newsapp.presentation

import com.kaya.newsapp.domain.models.Article

data class ArticleState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
)
