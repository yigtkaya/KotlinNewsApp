package com.kaya.newsapp.presentation.search

import androidx.lifecycle.ViewModel
import com.kaya.newsapp.data.repository.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NewsRepositoryImpl
) : ViewModel() {

    
}