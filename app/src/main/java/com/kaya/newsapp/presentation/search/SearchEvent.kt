package com.kaya.newsapp.presentation.search

import com.kaya.newsapp.domain.models.Article

sealed class SearchEvent {
    data class onSearchQueryChange(val searchQuery: String): SearchEvent()

    data class onClick(val article: Article): SearchEvent()
}