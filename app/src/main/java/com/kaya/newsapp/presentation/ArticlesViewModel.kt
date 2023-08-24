package com.kaya.newsapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaya.newsapp.domain.repository.NewsRepository
import com.kaya.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    var state by mutableStateOf(ArticleState())

    private var searchJob: Job? = null

    init {
        getNews()
    }

    fun onEvent(event: ArticleEvent) {
        when(event) {
            is ArticleEvent.Refresh -> {
                getNews(true)
            }
            is ArticleEvent.onSearchQueryChange -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    val startSearch = event.searchQuery.length >= 3
                    if (startSearch) {
                        delay(500L)
                        getNews()
                        return@launch
                    }
                }
            }
        }
    }

    private fun getNews(
        fetchFromRemote: Boolean = false,
        searchQuery: String = state.searchQuery.lowercase()
    ) {
            viewModelScope.launch {
                repository
                    .getNews(fetchFromRemote, searchQuery)
                    .collect { result ->
                        when(result) {
                            is Resource.Success -> {
                                result.data?.let {
                                    state = state.copy(articles = it)
                                }
                            }
                            is Resource.Error -> Unit
                            is Resource.Loading -> {
                                state = state.copy(isLoading = result.isLoading)
                            }
                        }
                    }
            }
    }
}