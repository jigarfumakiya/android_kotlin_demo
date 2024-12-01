package com.app.android_test.data.repository.news

import com.app.android_test.domain.model.NewsDomain

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */
 
interface NewsRepository {
    suspend fun getNewsHeadlines(): NewsDomain
}
 
