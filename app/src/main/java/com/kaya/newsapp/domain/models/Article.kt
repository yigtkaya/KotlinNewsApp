package com.kaya.newsapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Article (
    val type: String,
    val title: String?,
    val author: String?,
    val description: String?,
    val url: String?,
    val imageUrl: String?,
    val publishedAt: String?,
    val content: String?,
) : Parcelable