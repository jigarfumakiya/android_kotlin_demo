package com.app.android_test.data.mappers

import com.app.android_test.core.utility.extension.toEmpty
import com.app.android_test.data.network.dto.ArticleNetwork
import com.app.android_test.data.network.dto.NewsNetwork
import com.app.android_test.data.network.dto.SourceNetwork
import com.app.android_test.domain.model.ArticleDomain
import com.app.android_test.domain.model.NewsDomain
import com.app.android_test.domain.model.SourceDomain

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

fun NewsNetwork.toDomain() = NewsDomain(
    status = status.toEmpty(),
    totalResults = totalResults ?: 0,
    articles = articles?.map { it.toDomain() } ?: arrayListOf()
)

fun ArticleNetwork.toDomain() = ArticleDomain(
    source = source?.toDomain() ?: SourceDomain(),
    author = author.toEmpty(),
    title = title.toEmpty(),
    description = description.toEmpty(),
    url = url.toEmpty(),
    urlToImage = urlToImage.toEmpty(),
    publishedAt = publishedAt.toEmpty(),
    content = content.toEmpty()
)

fun SourceNetwork.toDomain() = SourceDomain(
    id = id.toEmpty(),
    name = name.toEmpty()
)

 
