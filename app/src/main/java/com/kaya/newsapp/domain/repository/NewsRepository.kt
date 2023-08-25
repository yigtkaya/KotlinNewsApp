package com.kaya.newsapp.domain.repository

import com.kaya.newsapp.domain.models.Article
import com.kaya.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(
        fetchFromRemote: Boolean,
        searchQuery: String,
        selectedTab : String,
    ) : Flow<Resource<List<Article>>>

    suspend fun getSportsNews(
        fetchFromRemote: Boolean,
        searchQuery: String,
        selectedTab : String,
        ) : Flow<Resource<List<Article>>>

    suspend fun getHealthNews(
        fetchFromRemote: Boolean,
        searchQuery: String,
        selectedTab : String,
        ) : Flow<Resource<List<Article>>>

    suspend fun getFinanceNews(
        fetchFromRemote: Boolean,
        searchQuery: String,
        selectedTab : String,
        ) : Flow<Resource<List<Article>>>

    suspend fun getAllNews(
        fetchFromRemote: Boolean = false,
        searchQuery: String,
        ) : Flow<Resource<List<Article>>>
}