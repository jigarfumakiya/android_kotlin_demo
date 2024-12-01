package com.app.android_test.data.network.dto

import kotlinx.serialization.Serializable

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

@Serializable
data class NewsNetwork (
    val status: String? = null,
    val totalResults: Long? = null,
    val articles: List<ArticleNetwork>? = null
)

@Serializable
data class ArticleNetwork (
    val source: SourceNetwork? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

@Serializable
data class SourceNetwork (
    val id: String? = null,
    val name: String? = null
)

