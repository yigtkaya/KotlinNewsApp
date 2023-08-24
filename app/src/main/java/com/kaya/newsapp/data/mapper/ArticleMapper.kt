package com.kaya.newsapp.data.mapper

import com.kaya.newsapp.data.local.ArticleEntity
import com.kaya.newsapp.domain.models.Article

fun ArticleEntity.toArticle() : Article {
    return Article(
        title = this.title,
        author = this.author,
        description = this.description,
        url = this.url,
        imageUrl = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}

fun Article.toArticleEntity() : ArticleEntity {
    return ArticleEntity(
        title = this.title,
        author = this.author,
        description = this.description,
        url = this.url,
        urlToImage = this.imageUrl,
        publishedAt = this.publishedAt,
        content = this.content
    )
}