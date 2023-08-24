package com.kaya.newsapp.data.local

import com.squareup.moshi.Json

data class NewsResponse(
    @field:Json(name = "articles")
    val articles: List<ArticleEntity>,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "totalResults")
    val totalResults: Int
)
