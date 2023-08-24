package com.kaya.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kaya.newsapp.domain.models.Source

@Entity
data class ArticleEntity(
    val title: String?,
    val author: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    @PrimaryKey val id : Int? = null
)
