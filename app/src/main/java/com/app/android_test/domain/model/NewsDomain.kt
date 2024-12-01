package com.app.android_test.domain.model

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

data class NewsDomain(
    val status: String = "",
    val totalResults: Long = 0L,
    val articles: List<ArticleDomain> = arrayListOf(),
)

data class ArticleDomain(
    val source: SourceDomain = SourceDomain(),
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
)

data class SourceDomain(
    val id: String = "",
    val name: String = ""
)

 
