package com.kaya.newsapp.presentation.search

sealed class SearchEvent {
    data class onSearchQueryChange(val searchQuery: String): SearchEvent()
}