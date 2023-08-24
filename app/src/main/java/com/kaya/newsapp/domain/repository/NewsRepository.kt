package com.kaya.newsapp.domain.repository

import com.kaya.newsapp.domain.models.Article
import com.kaya.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(
        fetchFromRemote: Boolean,
        searchQuery: String,
    ) : Flow<Resource<List<Article>>>

    suspend fun getSportsNews(
        fetchFromRemote: Boolean,
        ) : Flow<Resource<List<Article>>>

    suspend fun getHealthNews(
        fetchFromRemote: Boolean,
        ) : Flow<Resource<List<Article>>>

    suspend fun getFinanceNews(
        fetchFromRemote: Boolean,
        ) : Flow<Resource<List<Article>>>
}