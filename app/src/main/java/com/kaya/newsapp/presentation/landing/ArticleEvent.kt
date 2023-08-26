package com.kaya.newsapp.presentation.landing

sealed class ArticleEvent {
    object Refresh: ArticleEvent()
    data class onSearchQueryChange(val searchQuery: String): ArticleEvent()
    data class onSelectedTabChange(val selectedTab: String): ArticleEvent()
}