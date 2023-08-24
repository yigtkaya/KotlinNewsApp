package com.kaya.newsapp.presentation

sealed class ArticleEvent {
    object Refresh: ArticleEvent()
    data class onSearchQueryChange(val searchQuery: String): ArticleEvent()
}