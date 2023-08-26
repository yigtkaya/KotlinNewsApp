package com.kaya.newsapp.presentation.search

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
class SearchViewModel @Inject constructor(
    private val repository: NewsRepositoryImpl
) : ViewModel() {

    var state by mutableStateOf(SearchState())
    private var searchJob: Job? = null

    init {
        getAll()
    }
    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.onSearchQueryChange -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    val startSearch = event.searchQuery.length >= 2
                    if (startSearch) {
                        delay(500L)
                        getAll()
                        return@launch
                    } else {
                        getAll()
                    }
                }
            }
        }
    }
    private fun getAll(
        fetchFromRemote: Boolean = false,
        searchQuery: String = state.searchQuery.lowercase(),
    ) {
        viewModelScope.launch {
            repository
                .getAllNews(fetchFromRemote, searchQuery)
                .collect { result ->
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