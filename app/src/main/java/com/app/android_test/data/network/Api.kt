package com.app.android_test.data.network

import com.app.android_test.data.network.dto.NewsNetwork
import retrofit2.http.GET

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

interface Api {

    @GET("v2/top-headlines?sources=bbc-news")
    suspend fun getTopHeadlines(): NewsNetwork

}