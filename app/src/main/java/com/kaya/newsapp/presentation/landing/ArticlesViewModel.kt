package com.kaya.newsapp.presentation.landing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaya.newsapp.data.repository.NewsRepositoryImpl
import com.kaya.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val repository: NewsRepositoryImpl
): ViewModel() {

    var state by mutableStateOf(ArticleState())

    private var searchJob: Job? = null

    init {
        getNews()
        getSportsNews()
        getFinanceNews()
        getHealthNews()
    }

    fun onEvent(event: ArticleEvent) {
        when(event) {
            is ArticleEvent.Refresh -> {
                when(state.selectedTab) {
                    "Trending" -> {
                        getNews(true)
                    }
                    "Health" -> {
                        getHealthNews(true)
                    }
                    "Finance" -> {
                        getFinanceNews(true)
                    }
                    "Sports" -> {
                        getSportsNews(true)
                    }
                }
            }
            is ArticleEvent.onSelectedTabChange -> {
                state = state.copy(selectedTab = event.selectedTab)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(300L)
                    when(event.selectedTab) {
                        "Trending" -> {
                            getNews()
                            return@launch
                        }
                        "Health" -> {
                            getHealthNews()
                            return@launch
                        }
                        "Finance" -> {
                            getFinanceNews()
                            return@launch
                        }
                        "Sports" -> {
                            getSportsNews()
                            return@launch
                        }
                    }
                }
            }
        }
    }

    private fun getNews(
        fetchFromRemote: Boolean = false,
        searchQuery: String = state.searchQuery.lowercase(),
        selectedTab: String = state.selectedTab
    ) {
            viewModelScope.launch {
                repository
                    .getNews(fetchFromRemote, searchQuery, selectedTab)
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

    private fun getHealthNews(
        fetchFromRemote: Boolean = false,
        searchQuery: String = state.searchQuery.lowercase(),
        selectedTab: String = state.selectedTab
    ) {
        viewModelScope.launch {
            repository
                .getHealthNews(fetchFromRemote, searchQuery, selectedTab)
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

    private fun getFinanceNews(
        fetchFromRemote: Boolean = false,
        searchQuery: String = state.searchQuery.lowercase(),
        selectedTab: String = state.selectedTab
    ) {
        viewModelScope.launch {
            repository
                .getFinanceNews(fetchFromRemote, searchQuery, selectedTab)
                .collect {result ->
                    when (result) {
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

    private fun getSportsNews(
        fetchFromRemote: Boolean = false,
        searchQuery: String = state.searchQuery.lowercase(),
        selectedTab: String = state.selectedTab
    ) {
        viewModelScope.launch {
            repository
                .getSportsNews(fetchFromRemote, searchQuery, selectedTab)
                .collect {result ->
                    when (result) {
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